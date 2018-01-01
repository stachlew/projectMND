package pl.wat.mnd.db.domain.optymalization;

import javax.persistence.*;
//Status może być jakimś krokiem w algorytmie przetważania, lub po prostiu "W trakcie", "Gotowy"
@Entity
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_STATUS_SEQ")
    @SequenceGenerator(sequenceName = "ORDER_STATUS_SEQ", initialValue = 1, allocationSize = 1, name = "ORDER_STATUS_SEQ")
    public Long id;

    @Column
    public String description;
}
