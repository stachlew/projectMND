package pl.wat.mnd.db.repository.optymalization;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wat.mnd.db.domain.optymalization.Restrictions;

import java.util.List;

public interface RestrictionsRepository extends JpaRepository<Restrictions,Long> {
    List<Restrictions> getAllByOrderId(Long id);
}
