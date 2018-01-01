package pl.wat.mnd.db.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wat.mnd.db.domain.product.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByProductCategoryId(Long id);
}
