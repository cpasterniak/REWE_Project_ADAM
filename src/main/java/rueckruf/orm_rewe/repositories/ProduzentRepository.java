package rueckruf.orm_rewe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rueckruf.orm_rewe.entity.Produzent;

public interface ProduzentRepository extends JpaRepository<Produzent, Long> {}
