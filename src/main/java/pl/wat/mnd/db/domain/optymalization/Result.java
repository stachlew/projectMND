package pl.wat.mnd.db.domain.optymalization;

import pl.wat.mnd.db.domain.product.Product;

import javax.persistence.*;

@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESULT_SEQ")
    @SequenceGenerator(sequenceName = "RESULT_SEQ", initialValue = 1, allocationSize = 1, name = "RESULT_SEQ")
    public Long id;

    @ManyToOne
    public Product product;

    @Column
    public Integer quantity;

    @ManyToOne
    public PurchaseOrder order;

    public Result() {
    }

    public Result(Product product, Integer quantity, PurchaseOrder order) {
        this.product = product;
        this.quantity = quantity;
        this.order = order;
    }
}
