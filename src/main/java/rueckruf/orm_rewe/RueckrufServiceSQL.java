package rueckruf.orm_rewe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RueckrufServiceSQL {
    private final RueckrufRepository rueckrufRepository;
    private final ProductRepository productRepository;
    private final RueckrufProductRepository rueckrufProductRepository;

    private List<RueckrufWithProduct> rueckrufWithProduct;

    @Autowired
    public RueckrufServiceSQL(RueckrufRepository rueckrufRepository,
                              ProductRepository productRepository, RueckrufProductRepository rueckrufProductRepository) {
        this.rueckrufRepository = rueckrufRepository;
        this.productRepository = productRepository;
        this.rueckrufProductRepository = rueckrufProductRepository;
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

    public List<Rueckruf> findByFilter(String filter) {
        return findeRueckrufeNachFeld(filter.split(":")[0], filter.split(":")[1]);
    }

    public List<Rueckruf> findeRueckrufeNachFeld(String feldname, String wert) {
        SearchCriteria s = new SearchCriteria(feldname, ":", wert);
        System.out.println(s.getOperation());
        RueckrufSpecification r = new RueckrufSpecification(s);
        return rueckrufRepository.findAll(Specification.where(r));
    }


    private String capitalize(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1);
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