package rueckruf.orm_rewe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rueckruf.orm_rewe.entity.VersionControl;

public interface VersionControlRepository extends JpaRepository<VersionControl, Long> {}
