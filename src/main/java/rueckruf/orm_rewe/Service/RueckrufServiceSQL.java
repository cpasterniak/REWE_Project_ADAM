import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rueckruf.orm_rewe.entity.Product;
import rueckruf.orm_rewe.entity.Rueckruf;
import rueckruf.orm_rewe.entity.RueckrufWithProduct;
import rueckruf.orm_rewe.repositories.ProductRepository;
import rueckruf.orm_rewe.repositories.RueckrufRepository;

import java.util.List;

@Service
public class RueckrufServiceSQL {
    private final RueckrufRepository rueckrufRepository;
    private final ProductRepository productRepository;

    @Autowired
    public RueckrufServiceSQL(RueckrufRepository rueckrufRepository,
                              ProductRepository productRepository) {
        this.rueckrufRepository = rueckrufRepository;
        this.productRepository = productRepository;
    }

    public RueckrufWithProduct findById(Long id) {
        Rueckruf rueckruf = rueckrufRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rückruf nicht gefunden"));

        List<Product> products = productRepository.findByRueckrufId(id);

        return new RueckrufWithProduct(rueckruf, products);
    }
}