package rueckruf.orm_rewe;
import jakarta.persistence.*;

@Entity
@Table(name = "lieferant")
public class Lieferant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long liefranten_id;

    private String lieferantenname;
    private String lieferantadress;

    // Getter & Setter

    public Long getLiefranten_id() {
        return liefranten_id;
    }

    public void setLiefranten_id(Long liefranten_id) {
        this.liefranten_id = liefranten_id;
    }

    public String getLieferantenname() {
        return lieferantenname;
    }

    public void setLieferantenname(String lieferantenname) {
        this.lieferantenname = lieferantenname;
    }

    public String getLieferantadress() {
        return lieferantadress;
    }

    public void setLieferantadress(String lieferantadress) {
        this.lieferantadress = lieferantadress;
    }
}

