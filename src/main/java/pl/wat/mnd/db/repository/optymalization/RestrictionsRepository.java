package pl.wat.mnd.db.repository.optymalization;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wat.mnd.db.domain.optymalization.Restrictions;

public interface RestrictionsRepository extends JpaRepository<Restrictions,Long> {
}
