package pl.wat.mnd.db.repository.optymalization;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wat.mnd.db.domain.optymalization.PurchaseOrder;
import pl.wat.mnd.db.domain.optymalization.Result;

import javax.transaction.Transactional;
import java.util.List;

public interface ResultRepository extends JpaRepository<Result,Long> {
    @Transactional
    public void deleteAllByOrder(PurchaseOrder purchaseOrder);
}
