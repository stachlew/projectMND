package pl.wat.mnd.db.domain.product;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROD_SEQ")
    @SequenceGenerator(sequenceName = "PROD_SEQ", initialValue = 1, allocationSize = 1, name = "PROD_SEQ")
    Long id;

    @Column
    String name;

    @Column
    Double price;

    @Column
    Double kcalories;

    @ManyToOne
    ProductCategory productCategory;

}
