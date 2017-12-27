package pl.wat.mnd.db.domain.optymalization;

import pl.wat.mnd.db.domain.user.AplicationUser;

import javax.persistence.*;
import java.util.List;

@Entity
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQ")
    @SequenceGenerator(sequenceName = "ORDER_SEQ", initialValue = 1, allocationSize = 1, name = "ORDER_SEQ")
    Long id;

    @ManyToOne
    AplicationUser aplicationUser;

    @Column
    Double maxCost;

    @OneToMany(mappedBy = "order")
    List<Result> resultList;

    @ManyToOne
    OrderStatus orderStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AplicationUser getAplicationUser() {
        return aplicationUser;
    }

    public void setAplicationUser(AplicationUser aplicationUser) {
        this.aplicationUser = aplicationUser;
    }

    public Double getMaxCost() {
        return maxCost;
    }

    public void setMaxCost(Double maxCost) {
        this.maxCost = maxCost;
    }

    public List<Result> getResultList() {
        return resultList;
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
