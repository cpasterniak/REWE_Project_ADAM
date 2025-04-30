package rueckruf.orm_rewe.entity;
import jakarta.persistence.*;

@Entity
public class Cause {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long causeId;

    private String detailedCause;
    private Integer causeCategory;
    private Integer causeOption;

    private Boolean billa;
    private Boolean billaPlus;
    private Boolean billaKaufleute;
    private Boolean penny;
    private Boolean bipa;
    private Boolean sutterluety;
    private Boolean adeg;
    private Boolean ghKooperation;

    // Getters & Setters

    public Long getCauseId() {
        return causeId;
    }

    public void setCauseId(Long causeId) {
        this.causeId = causeId;
    }

    public String getDetailedCause() {
        return detailedCause;
    }

    public void setDetailedCause(String detailedCause) {
        this.detailedCause = detailedCause;
    }

    public Integer getCauseCategory() {
        return causeCategory;
    }

    public void setCauseCategory(Integer causeCategory) {
        this.causeCategory = causeCategory;
    }

    public Integer getCauseOption() {
        return causeOption;
    }

    public void setCauseOption(Integer causeOption) {
        this.causeOption = causeOption;
    }

    public Boolean getBilla() {
        return billa;
    }

    public void setBilla(Boolean billa) {
        this.billa = billa;
    }

    public Boolean getBillaPlus() {
        return billaPlus;
    }

    public void setBillaPlus(Boolean billaPlus) {
        this.billaPlus = billaPlus;
    }

    public Boolean getBillaKaufleute() {
        return billaKaufleute;
    }

    public void setBillaKaufleute(Boolean billaKaufleute) {
        this.billaKaufleute = billaKaufleute;
    }

    public Boolean getPenny() {
        return penny;
    }

    public void setPenny(Boolean penny) {
        this.penny = penny;
    }

    public Boolean getBipa() {
        return bipa;
    }

    public void setBipa(Boolean bipa) {
        this.bipa = bipa;
    }

    public Boolean getSutterluety() {
        return sutterluety;
    }

    public void setSutterluety(Boolean sutterluety) {
        this.sutterluety = sutterluety;
    }

    public Boolean getAdeg() {
        return adeg;
    }

    public void setAdeg(Boolean adeg) {
        this.adeg = adeg;
    }

    public Boolean getGhKooperation() {
        return ghKooperation;
    }

    public void setGhKooperation(Boolean ghKooperation) {
        this.ghKooperation = ghKooperation;
    }
}

