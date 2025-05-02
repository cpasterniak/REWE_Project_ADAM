package rueckruf.orm_rewe.repositories;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import rueckruf.orm_rewe.entity.Rueckruf;

import java.util.Optional;

// Beispiel für eine Repository-Klasse
public interface RueckrufRepository extends JpaRepository<Rueckruf, Long> {
    @NotNull Optional<Rueckruf> findById(@NotNull Long id);
}
