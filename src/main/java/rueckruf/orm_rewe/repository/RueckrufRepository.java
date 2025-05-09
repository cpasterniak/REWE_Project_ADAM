package rueckruf.orm_rewe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import rueckruf.orm_rewe.entity.Cause;
import rueckruf.orm_rewe.entity.Lieferant;
import rueckruf.orm_rewe.entity.Produzent;
import rueckruf.orm_rewe.entity.Rueckruf;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// Beispiel für eine Repository-Klasse
public interface RueckrufRepository extends JpaRepository, JpaSpecificationExecutor<Rueckruf> {

}
