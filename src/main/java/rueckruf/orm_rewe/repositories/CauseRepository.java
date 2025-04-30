package rueckruf.orm_rewe.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import rueckruf.orm_rewe.entity.Cause;

public interface CauseRepository extends JpaRepository<Cause, Long> {}

