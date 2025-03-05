package rueckruf.orm_rewe;
import jakarta.persistence.*;

@Entity
@Table(name = "produzent")
public class Produzent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long produzent_id;

    private String produzentname;
    private String produzentadress;

    // Getter & Setter

    public Long getProduzent_id() {
        return produzent_id;
    }

    public void setProduzent_id(Long produzent_id) {
        this.produzent_id = produzent_id;
    }

    public String getProduzentname() {
        return produzentname;
    }

    public void setProduzentname(String produzentname) {
        this.produzentname = produzentname;
    }

    public String getProduzentadress() {
        return produzentadress;
    }

    public void setProduzentadress(String produzentadress) {
        this.produzentadress = produzentadress;
    }
}
