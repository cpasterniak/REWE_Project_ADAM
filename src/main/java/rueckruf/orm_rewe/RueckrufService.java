package rueckruf.orm_rewe;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RueckrufService {

    private final RueckrufRepository rueckrufRepository;
    private final ProductRepository productRepository;
    private final RueckrufProductRepository rueckrufProductRepository;

    public Rueckruf createRueckruf(Rueckruf rueckruf) {
        return rueckrufRepository.save(rueckruf);
    }

    public List<Rueckruf> getAllRueckrufe() {
        return rueckrufRepository.findAll();
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
}