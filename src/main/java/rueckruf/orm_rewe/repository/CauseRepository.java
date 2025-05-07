package rueckruf.orm_rewe.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import rueckruf.orm_rewe.entity.Cause;

public interface CauseRepository extends JpaRepository<Cause, Long> {}

