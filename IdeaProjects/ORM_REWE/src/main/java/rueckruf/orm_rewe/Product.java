package rueckruf.orm_rewe;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "product")
public class Product  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    private String product_bezeichnung_markenname;
    private String product_bezeichnung;
    private Integer product_bezeichnung_grammatur;
    private String not_product_bezeichnung_markenname;
    private String not_product_bezeichnung;
    private String not_product_bezeichnung_grammatur;
    private String specific_articlenumber;
    private Long EAN;
    private String MHD_charge;
    private Integer warengruppe;
    private Boolean food;

    // Getter & Setter

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getProduct_bezeichnung_markenname() {
        return product_bezeichnung_markenname;
    }

    public void setProduct_bezeichnung_markenname(String product_bezeichnung_markenname) {
        this.product_bezeichnung_markenname = product_bezeichnung_markenname;
    }

    public String getProduct_bezeichnung() {
        return product_bezeichnung;
    }

    public void setProduct_bezeichnung(String product_bezeichnung) {
        this.product_bezeichnung = product_bezeichnung;
    }

    public Integer getProduct_bezeichnung_grammatur() {
        return product_bezeichnung_grammatur;
    }

    public void setProduct_bezeichnung_grammatur(Integer product_bezeichnung_grammatur) {
        this.product_bezeichnung_grammatur = product_bezeichnung_grammatur;
    }

    public String getNot_product_bezeichnung_markenname() {
        return not_product_bezeichnung_markenname;
    }

    public void setNot_product_bezeichnung_markenname(String not_product_bezeichnung_markenname) {
        this.not_product_bezeichnung_markenname = not_product_bezeichnung_markenname;
    }

    public String getNot_product_bezeichnung() {
        return not_product_bezeichnung;
    }

    public void setNot_product_bezeichnung(String not_product_bezeichnung) {
        this.not_product_bezeichnung = not_product_bezeichnung;
    }

    public String getNot_product_bezeichnung_grammatur() {
        return not_product_bezeichnung_grammatur;
    }

    public void setNot_product_bezeichnung_grammatur(String not_product_bezeichnung_grammatur) {
        this.not_product_bezeichnung_grammatur = not_product_bezeichnung_grammatur;
    }

    public String getSpecific_articlenumber() {
        return specific_articlenumber;
    }

    public void setSpecific_articlenumber(String specific_articlenumber) {
        this.specific_articlenumber = specific_articlenumber;
    }

    public Long getEAN() {
        return EAN;
    }

    public void setEAN(Long EAN) {
        this.EAN = EAN;
    }

    public String getMHD_charge() {
        return MHD_charge;
    }

    public void setMHD_charge(String MHD_charge) {
        this.MHD_charge = MHD_charge;
    }

    public Integer getWarengruppe() {
        return warengruppe;
    }

    public void setWarengruppe(Integer warengruppe) {
        this.warengruppe = warengruppe;
    }

    public Boolean getFood() {
        return food;
    }

    public void setFood(Boolean food) {
        this.food = food;
    }
}

