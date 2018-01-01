package pl.wat.mnd.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.mnd.db.domain.optymalization.PurchaseOrder;
import pl.wat.mnd.db.domain.optymalization.Restrictions;
import pl.wat.mnd.db.domain.optymalization.Result;
import pl.wat.mnd.db.domain.product.Product;
import pl.wat.mnd.db.repository.optymalization.OrderStatusRepository;
import pl.wat.mnd.db.repository.optymalization.PurchaseOrderRepository;
import pl.wat.mnd.db.repository.optymalization.RestrictionsRepository;
import pl.wat.mnd.db.repository.optymalization.ResultRepository;
import pl.wat.mnd.db.repository.product.ProductRepository;
import scpsolver.constraints.LinearBiggerThanEqualsConstraint;
import scpsolver.constraints.LinearEqualsConstraint;
import scpsolver.constraints.LinearSmallerThanEqualsConstraint;
import scpsolver.lpsolver.LinearProgramSolver;
import scpsolver.lpsolver.SolverFactory;
import scpsolver.problems.LinearProgram;

import java.util.LinkedList;
import java.util.List;

@Service
public class OptimizerService {

    @Autowired
    private OptimizerService optimizerService;
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private RestrictionsRepository restrictionsRepository;

    public double[] getKCalories(List<Product> products){
        double[] kcalories = new double[products.size()];
        for(int i = 0; i < kcalories.length; i++){
            kcalories[i] = products.get(i).kcalories;
        }
        return kcalories;
    }

    public double[] getPrices(List<Product> products){
        double[] prices = new double[products.size()];
        for(int i = 0; i < prices.length; i++){
            prices[i] = products.get(i).price;
        }
        return prices;
    }

    public double[] getBiggerEqualZeroProducts(List<Product> products, int index){
        double[] productConstraints = new double[products.size()];
        productConstraints[index] = 1.0;
        return productConstraints;
    }

    public double[] getProductsGroup(List<Product> products, Long groupId){
        double[] productsInGroup = new double[products.size()];
        for(int i = 0; i < productsInGroup.length; i++){
            if(products.get(i).productCategory.id == groupId){
                productsInGroup[i] = 1.0;
            }
            else {
                productsInGroup[i] = 0.0;
            }
        }
        return productsInGroup;
    }


    public void optymalizeOrder(int id){
        //DANE POCZĄTKOWE DLA PROCESU
        PurchaseOrder order = purchaseOrderRepository.findOne(Long.valueOf(id));
        order.setOrderStatus(orderStatusRepository.findOne(Long.valueOf(2)));
        resultRepository.deleteAllByOrder(order);
        purchaseOrderRepository.save(order);

        //DANE POCZĄTKOWE DLA MODELU
        List<Product> products = productRepository.findAll();
        Double maxCost = order.getMaxCost();
        List<Restrictions> restrictions = restrictionsRepository.getAllByOrderId(Long.valueOf(id));

        //TWORZENIE MODELU
        LinearProgram lp = new LinearProgram(getKCalories(products));   //funkcja celu skladajaca sie z tablicy (indeks_produktu - kalorycznosc)

        //Rozwiazania calkowitoliczbowe
        for(int i = 0; i<products.size(); i++){
            lp.setInteger(i);
        }

        //OGRANICZENIA
        //1. Maksymalny budżet
        lp.addConstraint(new LinearSmallerThanEqualsConstraint(getPrices(products), maxCost*100, "MaxCost"));

        //2. Nieujemna ilość produktów
        for(int i = 0; i<products.size(); i++){
            lp.addConstraint(new LinearBiggerThanEqualsConstraint(getBiggerEqualZeroProducts(products,i),0,"Positive"+products.get(i).id));
        }

        //3. Ograniczenia na ilość produktów z danej kategorii
        restrictions.forEach(restriction -> {
            if("<=".equals(restriction.sign)){
                lp.addConstraint(new LinearSmallerThanEqualsConstraint(getProductsGroup(products,restriction.productCategory.id), restriction.value, "rest"+restriction.id));
            }
            else if("=".equals(restriction.sign)){
                lp.addConstraint(new LinearEqualsConstraint(getProductsGroup(products,restriction.productCategory.id), restriction.value, "rest"+restriction.id));
            }
            else if(">=".equals(restriction.sign)){
                lp.addConstraint(new LinearBiggerThanEqualsConstraint(getProductsGroup(products,restriction.productCategory.id), restriction.value, "rest"+restriction.id));
            }
        });

        //ROZWIAZYWANIE PROBLEMU
        //1. Maksymalizacja wartosci funkcji celu (liczby kalorii)
        lp.setMinProblem(false);

        //2. Rozwiązanie zadania za pomocą metody SIMPLEX
        LinearProgramSolver solver  = SolverFactory.newDefault();
        double[] sol = solver.solve(lp);

        if(sol!=null){
            //2.1 Translacja tablicy wynikowej (indeks_produktu - ilość)
            List<Result> results = new LinkedList<>();
            for(int i = 0; i< sol.length; i++){
                if(sol[i]>0) {
                    results.add(new Result(productRepository.findOne(Long.valueOf(products.get(i).id)), (int) sol[i], order));
                }
            }

            //2.2  Zapis otrzymanych rezultatów
            results.forEach(result -> {resultRepository.save(result);});
        }

        //3. Zmiana statusu zamówienia
        order.setOrderStatus(orderStatusRepository.findOne(Long.valueOf(4)));
        purchaseOrderRepository.save(order);
    }
}
