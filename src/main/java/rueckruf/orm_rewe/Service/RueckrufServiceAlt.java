package rueckruf.orm_rewe.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import rueckruf.orm_rewe.entity.Product;
import rueckruf.orm_rewe.entity.Rueckruf;
import rueckruf.orm_rewe.entity.RueckrufProduct;
import rueckruf.orm_rewe.repositories.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RueckrufServiceAlt {

    private final List<String> spalten = List.of(
            "rueckruf_id", "year", "halfyear", "month", "day", "prue_number", "prue_freigabe",
            "art", "artdate", "pi_income_date", "type", "is_marke", "artikelanzahl_prue",
            "kassasperre", "wiederanlieferung", "createkundeninfo", "meldung", "meldung_date",
            "cm", "qm", "bm", "pm", "bearbeitung_beginn", "bearbeitung_ende",
            "artsperr", "anzahl_artsperr", "formular", "mandatar", "note", "cause_id",
            "lieferant_id", "produzent_id", "rechnung_name", "rechnung_betrag",
            "rechnung_sprache", "rechnung_ver_betrag", "rechnung_kontrolle", "status"
    );
    private final RueckrufRepository rueckrufRepository;
    private final ProductRepository productRepository;
    private final RueckrufProductRepository rueckrufProductRepository;

    public RueckrufServiceAlt(RueckrufRepository rueckrufRepository, ProductRepository productRepository, RueckrufProductRepository rueckrufProductRepository) {
        this.rueckrufRepository = rueckrufRepository;
        this.productRepository = productRepository;
        this.rueckrufProductRepository = rueckrufProductRepository;
    }

    public Rueckruf createRueckruf(Rueckruf rueckruf) {
        return rueckrufRepository.save(rueckruf);
    }

    public List<Rueckruf> getAllRueckrufeWithProducts() {
        List<Rueckruf> rueckrufe = rueckrufRepository.findAll();
        // Lazy Loading vermeiden: Produkte für jeden Rückruf laden
        rueckrufe.forEach(rueckruf ->
                rueckruf.getProducts().forEach(link -> link.getProduct().getProductBezeichnung()));
        return rueckrufe;
    }

    public Rueckruf getRueckrufById(Long id) {
        return rueckrufRepository.findById(id).orElse(null);
    }

    public List<Product> getAllProductsByRueckrufId(Long rueckrufId) {
        Rueckruf rueckruf = rueckrufRepository.findById(rueckrufId)
                .orElseThrow(() -> new RuntimeException("Rückruf nicht gefunden"));
        return rueckruf.getProducts().stream()
                .map(RueckrufProduct::getProduct)
                .collect(Collectors.toList());
    }

    public Rueckruf addProductToRueckruf(Long rueckrufId, Long productId) {
        Rueckruf rueckruf = rueckrufRepository.findById(rueckrufId)
                .orElseThrow(() -> new RuntimeException("Rueckruf not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        RueckrufProduct link = new RueckrufProduct();
        link.setRueckruf(rueckruf);
        link.setProduct(product);
        rueckrufProductRepository.save(link);

        rueckruf.getProducts().add(link);
        return rueckrufRepository.save(rueckruf);
    }


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void updateRueckrufSpalte(Long id, String value) {
        String column = seperateStrings(value)[0];
        String newValue = seperateStrings(value)[1];

        if (!spalten.contains(column)) {
            throw new IllegalArgumentException("Ungültiger Spaltenname: " + column);
        }

        String sql = "UPDATE rueckruf SET " + column + " = ? WHERE rueckruf_id = ?";
        jdbcTemplate.update(sql, newValue, id);
    }

    public List<Rueckruf> getRuerufByColumn(String value) {
        String column = seperateStrings(value)[0];
        String filtervalue = seperateStrings(value)[1];

        if (!spalten.contains(column)) {
            throw new IllegalArgumentException("Ungültiger Spaltenname: " + column);
        }

        String sql = "SELECT * FROM rueckruf WHERE " + column + " = ?";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Rueckruf.class), filtervalue);
    }



    public String[] seperateStrings(String string) {
        return string.split(":");
    }

}