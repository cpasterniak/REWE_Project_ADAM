package rueckruf.orm_rewe.Controller;

import org.springframework.web.bind.annotation.*;
import rueckruf.orm_rewe.Service.RueckrufService;
import rueckruf.orm_rewe.entity.RueckrufWithProduct;

import java.util.List;

@RestController
@RequestMapping("/api/rueckrufe")
public class RueckrufController {

    private final RueckrufService rueckrufService;

    public RueckrufController(RueckrufService rueckrufService) {
        this.rueckrufService = rueckrufService;
    }

    @GetMapping
    @ResponseBody
    public List<RueckrufWithProduct> getAllRueckrufeWithProducts() {
        return rueckrufService.getAllRueckrufe();
    }
}