package rueckruf.orm_rewe;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
@Setter
public class VersionControl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long versionId;

    private LocalDateTime versionDate = LocalDateTime.now();
    private String description;

    @ManyToOne
    @JoinColumn(name = "rueckruf_id")
    private Rueckruf rueckruf;
}




