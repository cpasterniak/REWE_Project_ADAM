package rueckruf.orm_rewe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
    private String status;

    @OneToMany(mappedBy = "rueckruf", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<RueckrufProduct> products = new ArrayList<>();

}


