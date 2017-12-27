package pl.wat.mnd.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.mnd.db.domain.optymalization.PurchaseOrder;
import pl.wat.mnd.db.domain.optymalization.Result;
import pl.wat.mnd.db.repository.optymalization.OrderStatusRepository;
import pl.wat.mnd.db.repository.optymalization.PurchaseOrderRepository;
import pl.wat.mnd.db.repository.optymalization.ResultRepository;
import pl.wat.mnd.db.repository.product.ProductRepository;

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

    public void optymalizeOrder(int id){

        PurchaseOrder order = purchaseOrderRepository.findOne(Long.valueOf(id));
        order.setOrderStatus(orderStatusRepository.findOne(Long.valueOf(2)));
        resultRepository.deleteAllByOrder(order);
        purchaseOrderRepository.save(order);


        List<Result> results = new LinkedList<>();
        results.add(new Result(productRepository.findOne(Long.valueOf(1)),7,order));
        results.add(new Result(productRepository.findOne(Long.valueOf(2)),3,order));

        results.forEach(result -> {resultRepository.save(result);});

        order.setOrderStatus(orderStatusRepository.findOne(Long.valueOf(4)));
        purchaseOrderRepository.save(order);

    }
}
