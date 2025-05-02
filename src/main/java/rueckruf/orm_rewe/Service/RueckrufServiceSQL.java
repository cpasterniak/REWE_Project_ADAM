package rueckruf.orm_rewe.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rueckruf.orm_rewe.entity.Product;
import rueckruf.orm_rewe.entity.Rueckruf;
import rueckruf.orm_rewe.entity.RueckrufWithProduct;
import rueckruf.orm_rewe.repositories.ProductRepository;
import rueckruf.orm_rewe.repositories.RueckrufProductRepository;
import rueckruf.orm_rewe.repositories.RueckrufRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RueckrufServiceSQL {
    private final RueckrufProductRepository rueckrufProductRepo;
    private final RueckrufRepository rueckrufRepository; // Neu hinzufügen
    private final ProductRepository productRepository;

    @Autowired
    public RueckrufServiceSQL(RueckrufProductRepository rueckrufProductRepo, RueckrufRepository rueckrufRepository, ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.rueckrufProductRepo = rueckrufProductRepo;
        this.rueckrufRepository = rueckrufRepository;
    }

    public List<RueckrufWithProduct> getAllRueckrufeWithProducts() {
        Map<Rueckruf, List<Product>> rueckrufProductsMap = new HashMap<>();

        rueckrufProductRepo.findAll().forEach(rp -> {
            rueckrufProductsMap.computeIfAbsent(rp.getRueckruf(), k -> new ArrayList<>())
                    .add(rp.getProduct());
        });

        return rueckrufProductsMap.entrySet().stream()
                .map(entry -> new RueckrufWithProduct(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public RueckrufWithProduct findById(Long id) {
        Rueckruf rueckruf = rueckrufRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rueckruf nicht gefunden"));

        List<Product> products = productRepository.findAllByRueckrufId(rueckruf.getRueckrufId());

        return new RueckrufWithProduct(rueckruf, products);
    }
}
