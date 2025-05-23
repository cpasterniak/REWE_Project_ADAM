package rueckruf.orm_rewe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rueckruf.orm_rewe.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
        SELECT rp.product
        FROM RueckrufProduct rp
        WHERE rp.rueckruf.rueckrufId = :rueckrufId
    """)
    List<Product> findAllByRueckrufId(@Param("rueckrufId") Long rueckrufId);
}

