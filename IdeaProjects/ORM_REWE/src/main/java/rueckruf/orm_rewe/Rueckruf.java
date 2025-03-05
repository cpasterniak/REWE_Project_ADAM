package rueckruf.orm_rewe;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "rueckruf")
public class Rueckruf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rueckruf_id;

    private Integer year;
    private Integer halfyear;
    private Integer month;
    private String day;
    private Integer prue_number;
    private LocalDate prue_freigabe;
    private String art;
    private LocalDate artdate;
    private LocalDate pi_income_date;
    private String type;
    private Boolean is_marke;
    private Integer artikelanzahl_prue;
    private LocalDate kassasperre;
    private LocalDate createkundeninfo;
    private Boolean meldung;
    private LocalDate meldung_date;
    private String cm;
    private String qm;
    private String bm;
    private String pm;
    private LocalDateTime bearbeitung_beginn;
    private LocalDateTime bearbeitung_ende;
    private String artsperr;
    private Integer anzahl_artsperr;
    private Boolean formular;
    private String mandatar;
    private Integer note;

    @ManyToOne
    @JoinColumn(name = "cause_id")
    private Cause cause;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "lieferant_id")
    private Lieferant lieferant;

    @ManyToOne
    @JoinColumn(name = "produzent_id")
    private Produzent produzent;

    @ManyToOne
    @JoinColumn(name = "version_id")
    private VersionControl version;

    private String rechnung_name;
    private Integer rechnung_betrag;
    private String rechnung_sprache;
    private Integer rechnung_ver_betrag;
    private String rechnung_kontrolle;

    // Getter & Setter

    public Long getRueckruf_id() {
        return rueckruf_id;
    }

    public void setRueckruf_id(Long rueckruf_id) {
        this.rueckruf_id = rueckruf_id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getHalfyear() {
        return halfyear;
    }

    public void setHalfyear(Integer halfyear) {
        this.halfyear = halfyear;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getPrue_number() {
        return prue_number;
    }

    public void setPrue_number(Integer prue_number) {
        this.prue_number = prue_number;
    }

    public LocalDate getPrue_freigabe() {
        return prue_freigabe;
    }

    public void setPrue_freigabe(LocalDate prue_freigabe) {
        this.prue_freigabe = prue_freigabe;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public LocalDate getArtdate() {
        return artdate;
    }

    public void setArtdate(LocalDate artdate) {
        this.artdate = artdate;
    }

    public LocalDate getPi_income_date() {
        return pi_income_date;
    }

    public void setPi_income_date(LocalDate pi_income_date) {
        this.pi_income_date = pi_income_date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIs_marke() {
        return is_marke;
    }

    public void setIs_marke(Boolean is_marke) {
        this.is_marke = is_marke;
    }

    public Integer getArtikelanzahl_prue() {
        return artikelanzahl_prue;
    }

    public void setArtikelanzahl_prue(Integer artikelanzahl_prue) {
        this.artikelanzahl_prue = artikelanzahl_prue;
    }

    public LocalDate getKassasperre() {
        return kassasperre;
    }

    public void setKassasperre(LocalDate kassasperre) {
        this.kassasperre = kassasperre;
    }

    public LocalDate getCreatekundeninfo() {
        return createkundeninfo;
    }

    public void setCreatekundeninfo(LocalDate createkundeninfo) {
        this.createkundeninfo = createkundeninfo;
    }

    public Boolean getMeldung() {
        return meldung;
    }

    public void setMeldung(Boolean meldung) {
        this.meldung = meldung;
    }

    public LocalDate getMeldung_date() {
        return meldung_date;
    }

    public void setMeldung_date(LocalDate meldung_date) {
        this.meldung_date = meldung_date;
    }

    public String getCm() {
        return cm;
    }

    public void setCm(String cm) {
        this.cm = cm;
    }

    public String getQm() {
        return qm;
    }

    public void setQm(String qm) {
        this.qm = qm;
    }

    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public LocalDateTime getBearbeitung_beginn() {
        return bearbeitung_beginn;
    }

    public void setBearbeitung_beginn(LocalDateTime bearbeitung_beginn) {
        this.bearbeitung_beginn = bearbeitung_beginn;
    }

    public LocalDateTime getBearbeitung_ende() {
        return bearbeitung_ende;
    }

    public void setBearbeitung_ende(LocalDateTime bearbeitung_ende) {
        this.bearbeitung_ende = bearbeitung_ende;
    }

    public String getArtsperr() {
        return artsperr;
    }

    public void setArtsperr(String artsperr) {
        this.artsperr = artsperr;
    }

    public Integer getAnzahl_artsperr() {
        return anzahl_artsperr;
    }

    public void setAnzahl_artsperr(Integer anzahl_artsperr) {
        this.anzahl_artsperr = anzahl_artsperr;
    }

    public Boolean getFormular() {
        return formular;
    }

    public void setFormular(Boolean formular) {
        this.formular = formular;
    }

    public String getMandatar() {
        return mandatar;
    }

    public void setMandatar(String mandatar) {
        this.mandatar = mandatar;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public Cause getCause() {
        return cause;
    }

    public void setCause(Cause cause) {
        this.cause = cause;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Lieferant getLieferant() {
        return lieferant;
    }

    public void setLieferant(Lieferant lieferant) {
        this.lieferant = lieferant;
    }

    public Produzent getProduzent() {
        return produzent;
    }

    public void setProduzent(Produzent produzent) {
        this.produzent = produzent;
    }

    public VersionControl getVersion() {
        return version;
    }

    public void setVersion(VersionControl version) {
        this.version = version;
    }

    public String getRechnung_name() {
        return rechnung_name;
    }

    public void setRechnung_name(String rechnung_name) {
        this.rechnung_name = rechnung_name;
    }

    public Integer getRechnung_betrag() {
        return rechnung_betrag;
    }

    public void setRechnung_betrag(Integer rechnung_betrag) {
        this.rechnung_betrag = rechnung_betrag;
    }

    public String getRechnung_sprache() {
        return rechnung_sprache;
    }

    public void setRechnung_sprache(String rechnung_sprache) {
        this.rechnung_sprache = rechnung_sprache;
    }

    public Integer getRechnung_ver_betrag() {
        return rechnung_ver_betrag;
    }

    public void setRechnung_ver_betrag(Integer rechnung_ver_betrag) {
        this.rechnung_ver_betrag = rechnung_ver_betrag;
    }

    public String getRechnung_kontrolle() {
        return rechnung_kontrolle;
    }

    public void setRechnung_kontrolle(String rechnung_kontrolle) {
        this.rechnung_kontrolle = rechnung_kontrolle;
    }
}
