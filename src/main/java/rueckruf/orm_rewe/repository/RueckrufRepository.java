package rueckruf.orm_rewe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rueckruf.orm_rewe.entity.Rueckruf;

// Beispiel für eine Repository-Klasse
public interface RueckrufRepository extends JpaRepository<Rueckruf, Long> {

}
