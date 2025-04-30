package rueckruf.orm_rewe.entity;
import jakarta.persistence.*;

@Entity
public class Lieferant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lieferantId;

    private String lieferantenname;
    private String lieferantadress;

    public Long getLieferantId() {
        return lieferantId;
    }

    public void setLieferantId(Long lieferantId) {
        this.lieferantId = lieferantId;
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

    // Getters & Setters
}



