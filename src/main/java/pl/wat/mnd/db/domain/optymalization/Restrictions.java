package pl.wat.mnd.db.domain.optymalization;

import pl.wat.mnd.db.domain.product.ProductCategory;

import javax.persistence.*;

@Entity
public class Restrictions {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESTRICTION_SEQ")
    @SequenceGenerator(sequenceName = "RESTRICTION_SEQ", initialValue = 1, allocationSize = 1, name = "RESTRICTION_SEQ")
    public Long id;

    @ManyToOne
    public ProductCategory productCategory;

    // >, >= , = , < , <=
    @Column
    public String sign;

    @Column
    public Integer value;

    @ManyToOne
    public PurchaseOrder order;
}
