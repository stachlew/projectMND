package pl.wat.mnd.db.domain.optymalization;

import pl.wat.mnd.db.domain.product.Product;

import javax.persistence.*;

@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESULT_SEQ")
    @SequenceGenerator(sequenceName = "RESULT_SEQ", initialValue = 1, allocationSize = 1, name = "RESULT_SEQ")
    Long id;

    @ManyToOne
    Product product;

    @Column
    Integer quantity;

    @ManyToOne
    Order order;


}
