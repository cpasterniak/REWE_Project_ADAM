package rueckruf.orm_rewe;
import jakarta.persistence.*;

@Entity
@Table(name = "cause")
public class Cause {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cause_id;

    private String detailed_cause;
    private Integer cause_category;
    private Integer cause_option;

    private Boolean billa;
    private Boolean billa_plus;
    private Boolean billa_kaufleute;
    private Boolean penny;
    private Boolean bipa;
    private Boolean sutterluety;
    private Boolean adeg;
    private Boolean ghKooperation;

    // Getter & Setter

    public Long getCause_id() {
        return cause_id;
    }

    public void setCause_id(Long cause_id) {
        this.cause_id = cause_id;
    }

    public String getDetailed_cause() {
        return detailed_cause;
    }

    public void setDetailed_cause(String detailed_cause) {
        this.detailed_cause = detailed_cause;
    }

    public Integer getCause_category() {
        return cause_category;
    }

    public void setCause_category(Integer cause_category) {
        this.cause_category = cause_category;
    }

    public Integer getCause_option() {
        return cause_option;
    }

    public void setCause_option(Integer cause_option) {
        this.cause_option = cause_option;
    }

    public Boolean getBilla() {
        return billa;
    }

    public void setBilla(Boolean billa) {
        this.billa = billa;
    }

    public Boolean getBilla_plus() {
        return billa_plus;
    }

    public void setBilla_plus(Boolean billa_plus) {
        this.billa_plus = billa_plus;
    }

    public Boolean getBilla_kaufleute() {
        return billa_kaufleute;
    }

    public void setBilla_kaufleute(Boolean billa_kaufleute) {
        this.billa_kaufleute = billa_kaufleute;
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

