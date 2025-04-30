package rueckruf.orm_rewe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rueckruf.orm_rewe.entity.RueckrufProduct;

public interface RueckrufProductRepository extends JpaRepository<RueckrufProduct, Long> {
}
