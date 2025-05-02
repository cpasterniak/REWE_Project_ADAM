package rueckruf.orm_rewe;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Produzent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long produzentId;

    private String produzentname;
    private String produzentadress;
}
