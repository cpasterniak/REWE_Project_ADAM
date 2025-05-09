package rueckruf.orm_rewe.Controller;

import org.springframework.web.bind.annotation.*;
import rueckruf.orm_rewe.Service.RueckrufServiceSQL;
import rueckruf.orm_rewe.entity.Product;
import rueckruf.orm_rewe.entity.Rueckruf;
import rueckruf.orm_rewe.entity.RueckrufProduct;
import rueckruf.orm_rewe.entity.RueckrufWithProduct;

import java.util.*;

@RestController
@RequestMapping("/api/rueckrufe")
public class RueckrufController {

    private final RueckrufServiceSQL rueckrufServiceSQL;

    public RueckrufController(RueckrufServiceSQL rueckrufServiceSQL) {
        this.rueckrufServiceSQL = rueckrufServiceSQL;
    }

    @GetMapping
    @ResponseBody
    public List<RueckrufWithProduct> getAllRueckrufeWithProducts() {
        List<RueckrufWithProduct> rwf = new ArrayList<>();
        for(Rueckruf r: rueckrufServiceSQL.findAll()) {
            rwf.add(JoinRueckrufProduct(r));
        }
        return rwf;
    }

    @GetMapping("/filter/{filter}")
    @ResponseBody
    public List<RueckrufWithProduct> getRueckrufeByFilter(@PathVariable String filter) {
        List<RueckrufWithProduct> rwf = new ArrayList<>();
        for(Rueckruf r: rueckrufServiceSQL.findByAnything(filter)) {
            rwf.add(JoinRueckrufProduct(r));
        }
        return rwf;
    }

    public RueckrufWithProduct JoinRueckrufProduct(Rueckruf rueckruf) {
        Map<Rueckruf, List<Product>> listMap = new HashMap<>();
        for (RueckrufProduct p : rueckruf.getProducts()) {
            System.out.println(p);
            listMap.computeIfAbsent(p.getRueckruf(),k -> new ArrayList<>()).add(p.getProduct());
        }
        return new RueckrufWithProduct(rueckruf, listMap.get(rueckruf));
    }
}