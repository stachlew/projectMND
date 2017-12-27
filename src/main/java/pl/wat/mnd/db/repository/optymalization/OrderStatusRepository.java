package pl.wat.mnd.db.repository.optymalization;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wat.mnd.db.domain.optymalization.OrderStatus;

public interface OrderStatusRepository extends JpaRepository<OrderStatus,Long> {
}
