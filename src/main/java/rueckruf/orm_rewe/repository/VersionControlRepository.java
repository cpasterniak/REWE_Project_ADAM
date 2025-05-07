package rueckruf.orm_rewe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rueckruf.orm_rewe.entity.VersionControl;

public interface VersionControlRepository extends JpaRepository<VersionControl, Long> {}
