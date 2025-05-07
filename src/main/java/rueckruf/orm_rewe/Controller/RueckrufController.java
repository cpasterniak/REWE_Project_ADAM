package rueckruf.orm_rewe.Controller;

import org.springframework.web.bind.annotation.*;
import rueckruf.orm_rewe.Service.RueckrufService;
import rueckruf.orm_rewe.Service.RueckrufServiceSQL;
import rueckruf.orm_rewe.entity.Rueckruf;
import rueckruf.orm_rewe.entity.RueckrufWithProduct;

import java.util.List;

@RestController
@RequestMapping("/api/rueckrufe")
public class RueckrufController {

    private final RueckrufService rueckrufService;
    private final RueckrufServiceSQL rueckrufServiceSQL;

    public RueckrufController(RueckrufService rueckrufService, RueckrufServiceSQL rueckrufServiceSQL) {
        this.rueckrufService = rueckrufService;
        this.rueckrufServiceSQL = rueckrufServiceSQL;
    }

    @GetMapping
    @ResponseBody
    public List<RueckrufWithProduct> getAllRueckrufeWithProducts() {
        return rueckrufService.getAllRueckrufe();
    }

    @GetMapping("/filter/{filter}")
    public List<Rueckruf> getRueckrufeByFilter(@PathVariable String filter) {
        return rueckrufServiceSQL.findByAnything(filter);
    }
}