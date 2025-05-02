package rueckruf.orm_rewe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rueckruf.orm_rewe.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Benutzerdefinierte Methoden
    List<Product> findByRueckrufId(Long rueckrufId);
}