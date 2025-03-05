package rueckruf.orm_rewe;

import org.springframework.data.jpa.repository.JpaRepository;

// Beispiel für eine Repository-Klasse
public interface RueckrufRepository extends JpaRepository<Rueckruf, Long> {}
