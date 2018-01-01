package pl.wat.mnd.db.domain.product;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROD_SEQ")
    @SequenceGenerator(sequenceName = "PROD_SEQ", initialValue = 1, allocationSize = 1, name = "PROD_SEQ")
    public Long id;

    @Column
    public String name;

    @Column
    public Double price;

    @Column
    public Double kcalories;

    @ManyToOne
    public ProductCategory productCategory;



}
