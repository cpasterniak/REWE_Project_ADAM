package rueckruf.orm_rewe.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import rueckruf.orm_rewe.RueckrufSpecification;
import rueckruf.orm_rewe.SQLSELECT.DynamischeFilterung;
import rueckruf.orm_rewe.SearchCriteria;
import rueckruf.orm_rewe.entity.Product;
import rueckruf.orm_rewe.entity.Rueckruf;
import rueckruf.orm_rewe.entity.RueckrufProduct;
import rueckruf.orm_rewe.entity.RueckrufWithProduct;
import rueckruf.orm_rewe.repository.ProductRepository;
import rueckruf.orm_rewe.repository.RueckrufProductRepository;
import rueckruf.orm_rewe.repository.RueckrufRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RueckrufServiceSQL {
    private final RueckrufRepository rueckrufRepository;
    private final ProductRepository productRepository;
    private final RueckrufProductRepository rueckrufProductRepository;
    private final JdbcTemplate jdbcTemplate;

    private List<RueckrufWithProduct> rueckrufWithProduct;

    @Autowired
    public RueckrufServiceSQL(RueckrufRepository rueckrufRepository,
                              ProductRepository productRepository, RueckrufProductRepository rueckrufProductRepository, JdbcTemplate jdbcTemplate) {
        this.rueckrufRepository = rueckrufRepository;
        this.productRepository = productRepository;
        this.rueckrufProductRepository = rueckrufProductRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public RueckrufWithProduct findById(Long id) {
        Rueckruf rueckruf = rueckrufRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rückruf nicht gefunden"));

        List<Product> products = productRepository.findAllByRueckrufId(id);

        return new RueckrufWithProduct(rueckruf, products);
    }

    public List<RueckrufWithProduct> findAll() {
        JoinRueckrufProduct();
        return this.rueckrufWithProduct;
    }

    public Rueckruf findByAnything(String query) {
        String sql = new DynamischeFilterung(query).getQuery();

        jdbcTemplate.query(sql, Rueckruf.class);
    }

    public void JoinRueckrufProduct() {
        List<RueckrufWithProduct> rueckrufe = new ArrayList<>();
        Map<Rueckruf, List<Product>> listMap = new HashMap<>();
        for (RueckrufProduct p : rueckrufProductRepository.findAll()) {
            System.out.println(p);
            listMap.computeIfAbsent(p.getRueckruf(),k -> new ArrayList<>()).add(p.getProduct());
        }
        for (Rueckruf r : listMap.keySet()) {
            RueckrufWithProduct rwp = new RueckrufWithProduct(r, listMap.get(r));
            rueckrufe.add(rwp);
        }
        this.rueckrufWithProduct = rueckrufe;
    }
}