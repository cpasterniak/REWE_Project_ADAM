package rueckruf.orm_rewe.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class VersionControl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long versionId;

    private LocalDateTime versionDate = LocalDateTime.now();
    private String description;

    @ManyToOne
    @JoinColumn(name = "rueckruf_id")
    private Rueckruf rueckruf;

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    public LocalDateTime getVersionDate() {
        return versionDate;
    }

    public void setVersionDate(LocalDateTime versionDate) {
        this.versionDate = versionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Rueckruf getRueckruf() {
        return rueckruf;
    }

    public void setRueckruf(Rueckruf rueckruf) {
        this.rueckruf = rueckruf;
    }
}




