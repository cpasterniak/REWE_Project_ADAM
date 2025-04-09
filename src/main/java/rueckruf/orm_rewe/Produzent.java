package rueckruf.orm_rewe;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Produzent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long produzentId;

    private String produzentname;
    private String produzentadress;

    public Long getProduzentId() {
        return produzentId;
    }

    public void setProduzentId(Long produzentId) {
        this.produzentId = produzentId;
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

    // Getters & Setters
}
