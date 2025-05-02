/**package rueckruf.orm_rewe.Controller;

import org.springframework.web.bind.annotation.*;
import rueckruf.orm_rewe.Service.RueckrufServiceAlt;
import rueckruf.orm_rewe.entity.Product;
import rueckruf.orm_rewe.entity.Rueckruf;
import rueckruf.orm_rewe.entity.RueckrufProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rueckrufe")
public class RueckrufControllerAlt {

    private final RueckrufServiceAlt rueckrufService;

    public RueckrufControllerAlt(RueckrufServiceAlt rueckrufService) {
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

    @GetMapping("filter/{columnValue}")
    public List<RueckrufWithProductsResponse> getRueckrufByColumnValue(@PathVariable String columnValue) {
        List<Rueckruf> rueckruf = rueckrufService.getRuerufByColumn(columnValue);
        if (rueckruf == null) {
            throw new RuntimeException("Rückruf nicht gefunden");
        }

        List<RueckrufWithProductsResponse> rueckgabe = new ArrayList<>();

        for (Rueckruf rueckruf1 : rueckruf) {
            Long id = rueckruf1.getRueckrufId();
            List<Product> products = rueckrufService.getAllProductsByRueckrufId(id);
            rueckgabe.add(new RueckrufWithProductsResponse(rueckruf1, products));
        }
        return rueckgabe;
    }

    @GetMapping("update/{id}/{update}")
    public String UpdateRueckruf(@PathVariable Long id, @PathVariable String update) {
        rueckrufService.updateRueckrufSpalte(id, update);
        System.out.println("Rueckruf update: " + update);
        return "Tod";
    }

    @GetMapping
    public List<RueckrufWithProductsResponse> getAllRueckrufeWithProducts() {
        List<Rueckruf> rueckrufe = rueckrufService.getAllRueckrufeWithProducts();
        return rueckrufe.stream()
                .map(rueckruf -> new RueckrufWithProductsResponse(
                        rueckruf,
                        rueckruf.getProducts().stream()
                                .map(RueckrufProduct::getProduct)
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
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
}*/