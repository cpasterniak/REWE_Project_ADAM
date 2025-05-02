package rueckruf.orm_rewe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<RueckrufWithProduct> findByFilter(String filter) {
        JoinRueckrufProduct();
        System.out.println(filter);
        System.out.println(this.rueckrufRepository.findByQm(filter).size());
        for(Rueckruf r : this.rueckrufRepository.findByQm(filter)) {
            System.out.println("Hello");
            System.out.println(r.getLieferant().getLieferantenname());
        }
        return this.rueckrufWithProduct;
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