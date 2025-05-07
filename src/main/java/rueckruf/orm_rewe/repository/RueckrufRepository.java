package rueckruf.orm_rewe.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import rueckruf.orm_rewe.entity.Cause;
import rueckruf.orm_rewe.entity.Lieferant;
import rueckruf.orm_rewe.entity.Produzent;
import rueckruf.orm_rewe.entity.Rueckruf;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// Beispiel für eine Repository-Klasse
public interface RueckrufRepository extends JpaRepository<Rueckruf, Long>, JpaSpecificationExecutor<Rueckruf> {
    @NotNull Optional<Rueckruf> findById(@NotNull Long id);

    List<Rueckruf> findByYear(int year);
    List<Rueckruf> findByHalfyear(int halfyear);
    List<Rueckruf> findByMonth(int month);
    List<Rueckruf> findByDay(String day);
    List<Rueckruf> findByPruenumber(int pruenumber);
    List<Rueckruf> findByPruefreigabe(LocalDate prueFreigabe);
    List<Rueckruf> findByPiincomedate(LocalDate piIncomeDate);
    List<Rueckruf> findByType(String type);
    List<Rueckruf> findByIsmarke(Boolean ismarke);
    List<Rueckruf> findByArtikelanzahlprue(int artikelanzahl);
    List<Rueckruf> findByKassasperre(LocalDate kassasperre);
    List<Rueckruf> findByWiederanlieferung(LocalDate wiederanlieferung);
    List<Rueckruf> findByCreatekundeninfo(LocalDate createkundeninfo);
    List<Rueckruf> findByMeldung(Boolean meldung);
    List<Rueckruf> findByMeldungdate(LocalDate meldungdate);
    List<Rueckruf> findByBearbeitungbeginn(LocalDateTime bearbeitungbeginn);
    List<Rueckruf> findByBearbeitungende(LocalDateTime bearbeitungEnde);
    List<Rueckruf> findByCm(String cm);
    List<Rueckruf> findByQm(String qm);
    List<Rueckruf> findByBm(String bm);
    List<Rueckruf> findByPm(String pm);
    List<Rueckruf> findByArtsperr(String artsperre);
    List<Rueckruf> findByAnzahlartsperr(int anzahlartsperre);
    List<Rueckruf> findByFormular(boolean formular);
    List<Rueckruf> findByMandatar(String mandatar);
    List<Rueckruf> findByNote(int note);
    List<Rueckruf> findByCause(Cause cause);
    List<Rueckruf> findByLieferant(Lieferant lieferant);
    List<Rueckruf> findByProduzent(Produzent produzent);
    List<Rueckruf> findByRechnungname(String rechnungname);
    List<Rueckruf> findByRechnungbetrag(int rechnungbetrag);
    List<Rueckruf> findByRechnungsprache(String rechnungsprache);
    List<Rueckruf> findByRechnungverbetrag(int rechnungverbetrag);
    List<Rueckruf> findByRechnungkontrolle(String rechnungkontrolle);
    List<Rueckruf> findByStatus(String status);

}
