package rueckruf.orm_rewe.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Setter
public class Lieferant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lieferantId;

    private String lieferantenname;
    private String lieferantadress;
}



