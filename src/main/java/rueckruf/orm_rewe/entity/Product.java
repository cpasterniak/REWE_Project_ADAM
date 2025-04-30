package rueckruf.orm_rewe.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productBezeichnungMarkenname;
    private String productBezeichnung;
    private Integer productBezeichnungGrammatur;

    private String notProductBezeichnungMarkenname;
    private String notProductBezeichnung;
    private String notProductBezeichnungGrammatur;

    private String specificArticlenumber;
    private Long ean;
    private String mhdCharge;

    private Integer warengruppe;
    private Boolean food;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference("product-ref")
    private List<RueckrufProduct> rueckrufe = new ArrayList<>();

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductBezeichnungMarkenname() {
        return productBezeichnungMarkenname;
    }

    public void setProductBezeichnungMarkenname(String productBezeichnungMarkenname) {
        this.productBezeichnungMarkenname = productBezeichnungMarkenname;
    }

    public String getProductBezeichnung() {
        return productBezeichnung;
    }

    public void setProductBezeichnung(String productBezeichnung) {
        this.productBezeichnung = productBezeichnung;
    }

    public Integer getProductBezeichnungGrammatur() {
        return productBezeichnungGrammatur;
    }

    public void setProductBezeichnungGrammatur(Integer productBezeichnungGrammatur) {
        this.productBezeichnungGrammatur = productBezeichnungGrammatur;
    }

    public String getNotProductBezeichnungMarkenname() {
        return notProductBezeichnungMarkenname;
    }

    public void setNotProductBezeichnungMarkenname(String notProductBezeichnungMarkenname) {
        this.notProductBezeichnungMarkenname = notProductBezeichnungMarkenname;
    }

    public String getNotProductBezeichnung() {
        return notProductBezeichnung;
    }

    public void setNotProductBezeichnung(String notProductBezeichnung) {
        this.notProductBezeichnung = notProductBezeichnung;
    }

    public String getNotProductBezeichnungGrammatur() {
        return notProductBezeichnungGrammatur;
    }

    public void setNotProductBezeichnungGrammatur(String notProductBezeichnungGrammatur) {
        this.notProductBezeichnungGrammatur = notProductBezeichnungGrammatur;
    }

    public String getSpecificArticlenumber() {
        return specificArticlenumber;
    }

    public void setSpecificArticlenumber(String specificArticlenumber) {
        this.specificArticlenumber = specificArticlenumber;
    }

    public Long getEan() {
        return ean;
    }

    public void setEan(Long ean) {
        this.ean = ean;
    }

    public String getMhdCharge() {
        return mhdCharge;
    }

    public void setMhdCharge(String mhdCharge) {
        this.mhdCharge = mhdCharge;
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

    public List<RueckrufProduct> getRueckrufe() {
        return rueckrufe;
    }

    public void setRueckrufe(List<RueckrufProduct> rueckrufe) {
        this.rueckrufe = rueckrufe;
    }
}




