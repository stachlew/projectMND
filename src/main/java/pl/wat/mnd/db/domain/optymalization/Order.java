package pl.wat.mnd.db.domain.optymalization;

import pl.wat.mnd.db.domain.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQ")
    @SequenceGenerator(sequenceName = "ORDER_SEQ", initialValue = 1, allocationSize = 1, name = "ORDER_SEQ")
    Long id;

    @ManyToOne
    User user;

    @Column
    Double maxCost;

    @OneToMany(mappedBy = "order")
    List<Result> resultList;

    @ManyToOne
    OrderStatus orderStatus;



}
