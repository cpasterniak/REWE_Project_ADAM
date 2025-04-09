package rueckruf.orm_rewe;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Rueckruf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rueckrufId;

    private Integer year;
    private Integer halfyear;
    private Integer month;
    private String day;

    private Integer prueNumber;
    private LocalDate prueFreigabe;

    private String art;
    private LocalDate artdate;
    private LocalDate piIncomeDate;

    private String type;
    private Boolean isMarke;
    private Integer artikelanzahlPrue;

    private LocalDate kassasperre;

    @Formula("kassasperre + INTERVAL '1 day'")
    private LocalDate wiederanlieferung;

    private LocalDate createkundeninfo;
    private Boolean meldung;
    private LocalDate meldungDate;

    private String cm, qm, bm, pm;

    private LocalDateTime bearbeitungBeginn;
    private LocalDateTime bearbeitungEnde;

    private String artsperr;
    private Integer anzahlArtsperr;

    private Boolean formular;
    private String mandatar;
    private Integer note;

    @ManyToOne
    @JoinColumn(name = "cause_id")
    private Cause cause;

    @ManyToOne
    @JoinColumn(name = "lieferant_id")
    private Lieferant lieferant;

    @ManyToOne
    @JoinColumn(name = "produzent_id")
    private Produzent produzent;

    private String rechnungName;
    private Integer rechnungBetrag;
    private String rechnungSprache;
    private Integer rechnungVerBetrag;
    private String rechnungKontrolle;

    @OneToMany(mappedBy = "rueckruf", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<RueckrufProduct> products = new ArrayList<>();

    public Long getRueckrufId() {
        return rueckrufId;
    }

    public void setRueckrufId(Long rueckrufId) {
        this.rueckrufId = rueckrufId;
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

    public Integer getPrueNumber() {
        return prueNumber;
    }

    public void setPrueNumber(Integer prueNumber) {
        this.prueNumber = prueNumber;
    }

    public LocalDate getPrueFreigabe() {
        return prueFreigabe;
    }

    public void setPrueFreigabe(LocalDate prueFreigabe) {
        this.prueFreigabe = prueFreigabe;
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

    public LocalDate getPiIncomeDate() {
        return piIncomeDate;
    }

    public void setPiIncomeDate(LocalDate piIncomeDate) {
        this.piIncomeDate = piIncomeDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getMarke() {
        return isMarke;
    }

    public void setMarke(Boolean marke) {
        isMarke = marke;
    }

    public Integer getArtikelanzahlPrue() {
        return artikelanzahlPrue;
    }

    public void setArtikelanzahlPrue(Integer artikelanzahlPrue) {
        this.artikelanzahlPrue = artikelanzahlPrue;
    }

    public LocalDate getKassasperre() {
        return kassasperre;
    }

    public void setKassasperre(LocalDate kassasperre) {
        this.kassasperre = kassasperre;
    }

    public LocalDate getWiederanlieferung() {
        return wiederanlieferung;
    }

    public void setWiederanlieferung(LocalDate wiederanlieferung) {
        this.wiederanlieferung = wiederanlieferung;
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

    public LocalDate getMeldungDate() {
        return meldungDate;
    }

    public void setMeldungDate(LocalDate meldungDate) {
        this.meldungDate = meldungDate;
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

    public LocalDateTime getBearbeitungBeginn() {
        return bearbeitungBeginn;
    }

    public void setBearbeitungBeginn(LocalDateTime bearbeitungBeginn) {
        this.bearbeitungBeginn = bearbeitungBeginn;
    }

    public LocalDateTime getBearbeitungEnde() {
        return bearbeitungEnde;
    }

    public void setBearbeitungEnde(LocalDateTime bearbeitungEnde) {
        this.bearbeitungEnde = bearbeitungEnde;
    }

    public String getArtsperr() {
        return artsperr;
    }

    public void setArtsperr(String artsperr) {
        this.artsperr = artsperr;
    }

    public Integer getAnzahlArtsperr() {
        return anzahlArtsperr;
    }

    public void setAnzahlArtsperr(Integer anzahlArtsperr) {
        this.anzahlArtsperr = anzahlArtsperr;
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

    public String getRechnungName() {
        return rechnungName;
    }

    public void setRechnungName(String rechnungName) {
        this.rechnungName = rechnungName;
    }

    public Integer getRechnungBetrag() {
        return rechnungBetrag;
    }

    public void setRechnungBetrag(Integer rechnungBetrag) {
        this.rechnungBetrag = rechnungBetrag;
    }

    public String getRechnungSprache() {
        return rechnungSprache;
    }

    public void setRechnungSprache(String rechnungSprache) {
        this.rechnungSprache = rechnungSprache;
    }

    public Integer getRechnungVerBetrag() {
        return rechnungVerBetrag;
    }

    public void setRechnungVerBetrag(Integer rechnungVerBetrag) {
        this.rechnungVerBetrag = rechnungVerBetrag;
    }

    public String getRechnungKontrolle() {
        return rechnungKontrolle;
    }

    public void setRechnungKontrolle(String rechnungKontrolle) {
        this.rechnungKontrolle = rechnungKontrolle;
    }

    public List<RueckrufProduct> getProducts() {
        return products;
    }

    public void setProducts(List<RueckrufProduct> products) {
        this.products = products;
    }
}


