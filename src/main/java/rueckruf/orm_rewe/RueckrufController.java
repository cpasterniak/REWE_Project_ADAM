package rueckruf.orm_rewe;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rueckrufe")
public class RueckrufController {

    private final RueckrufService rueckrufService;

    public RueckrufController(RueckrufService rueckrufService) {
        this.rueckrufService = rueckrufService;
    }

    @GetMapping("/{id}/products")
    public RueckrufWithProductsResponse getRueckrufWithProducts(@PathVariable Long id) {
        Rueckruf rueckruf = rueckrufService.getRueckrufById(id);
        if (rueckruf == null) {
            throw new RuntimeException("Rückruf nicht gefunden");
        }

        List<Product> products = rueckrufService.getAllProductsByRueckrufId(id);
        return new RueckrufWithProductsResponse(rueckruf, products);
    }

    // DTO für die Response
    public static class RueckrufWithProductsResponse {
        private Rueckruf rueckruf;
        private List<Product> products;

        public RueckrufWithProductsResponse(Rueckruf rueckruf, List<Product> products) {
            this.rueckruf = rueckruf;
            this.products = products;
        }

        // Getter
        public Rueckruf getRueckruf() {
            return rueckruf;
        }

        public List<Product> getProducts() {
            return products;
        }
    }
}