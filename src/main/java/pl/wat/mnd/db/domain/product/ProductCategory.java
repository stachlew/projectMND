package pl.wat.mnd.db.domain.product;

import javax.persistence.*;

@Entity
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAT_PROD_SEQ")
    @SequenceGenerator(sequenceName = "CAT_PROD_SEQ", initialValue = 1, allocationSize = 1, name = "CAT_PROD_SEQ")
    public Long id;

    @Column
    public String name;


}
