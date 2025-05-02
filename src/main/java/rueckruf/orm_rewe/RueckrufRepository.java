package rueckruf.orm_rewe;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// Beispiel für eine Repository-Klasse
public interface RueckrufRepository extends JpaRepository<Rueckruf, Long> {
    @NotNull Optional<Rueckruf> findById(@NotNull Long id);

    List<Rueckruf> findByQm(String qm);
}
