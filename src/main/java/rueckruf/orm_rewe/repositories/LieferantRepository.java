package rueckruf.orm_rewe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rueckruf.orm_rewe.entity.Lieferant;

public interface LieferantRepository extends JpaRepository<Lieferant, Long> {}
