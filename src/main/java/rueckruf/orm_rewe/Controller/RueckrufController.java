package rueckruf.orm_rewe.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rueckruf.orm_rewe.Service.RueckrufServiceSQL;
import rueckruf.orm_rewe.entity.Product;
import rueckruf.orm_rewe.entity.Rueckruf;
import rueckruf.orm_rewe.entity.RueckrufProduct;
import rueckruf.orm_rewe.entity.RueckrufWithProduct;
import rueckruf.orm_rewe.repository.RueckrufProductRepository;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@RestController
@RequestMapping("/api/rueckrufe")
public class RueckrufController {

    private final RueckrufServiceSQL rueckrufServiceSQL;
    private final RueckrufProductRepository rueckrufProductRepository;

    public RueckrufController(RueckrufServiceSQL rueckrufServiceSQL, RueckrufProductRepository rueckrufProductRepository) {
        this.rueckrufServiceSQL = rueckrufServiceSQL;
        this.rueckrufProductRepository = rueckrufProductRepository;
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
        Map<Long, RueckrufWithProduct> rwf = new HashMap<>();
        for(Rueckruf r: rueckrufServiceSQL.findByAnything(filter)) {
            for(RueckrufProduct rp: rueckrufProductRepository.findAll()) {
                System.out.println(rp.getRueckruf().getRueckrufId());
                System.out.println(r.getRueckrufId());
                System.out.println("");
                if (rp.getRueckruf().getRueckrufId().equals(r.getRueckrufId())) {
                    rwf.put(rp.getRueckruf().getRueckrufId(), JoinRueckrufProduct(rp.getRueckruf()));
                    System.out.println(r.getProducts().size());
                }
            }
        }
        return new ArrayList<>(rwf.values());
    }

    @GetMapping("/filter2/{filter}")
    @ResponseBody
    public List<RueckrufWithProduct> getRueckrufeByFilter2(@PathVariable String filter) {
        return rueckrufServiceSQL.findByAnything2(filter);
    }

    @GetMapping("/update/{value}/{id}")
    public ResponseEntity<?> updateRueckruf(@PathVariable String value, @PathVariable Long id) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        rueckrufServiceSQL.updateRueckruf(value, id);
        return ResponseEntity.ok().body("Rueckruf updated");
    }

    public RueckrufWithProduct JoinRueckrufProduct(Rueckruf rueckruf) {
        Map<Rueckruf, List<Product>> listMap = new HashMap<>();
        for (RueckrufProduct p : rueckruf.getProducts()) {
            System.out.println(p);
            listMap.computeIfAbsent(p.getRueckruf(), _ -> new ArrayList<>()).add(p.getProduct());
        }
        return new RueckrufWithProduct(rueckruf, listMap.get(rueckruf));
    }
}