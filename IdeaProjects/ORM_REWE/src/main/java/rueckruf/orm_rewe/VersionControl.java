package rueckruf.orm_rewe;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "versioncontrol")
public class VersionControl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long version_id;

    private LocalDateTime version_date;
    private String description;

    // Getter & Setter

    public Long getVersion_id() {
        return version_id;
    }

    public void setVersion_id(Long version_id) {
        this.version_id = version_id;
    }

    public LocalDateTime getVersion_date() {
        return version_date;
    }

    public void setVersion_date(LocalDateTime version_date) {
        this.version_date = version_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

