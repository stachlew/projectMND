package pl.wat.mnd.db.repository.optymalization;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wat.mnd.db.domain.optymalization.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder,Long> {
}
