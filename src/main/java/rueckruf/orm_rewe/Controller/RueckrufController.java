package rueckruf.orm_rewe.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rueckruf.orm_rewe.Service.RueckrufService;
import rueckruf.orm_rewe.entity.RueckrufProduct;
import rueckruf.orm_rewe.entity.RueckrufWithProduct;

import java.util.List;

@RestController
@RequestMapping("/rueckruf")
public class RueckrufController {
    private final RueckrufService rueckrufService;

    public RueckrufController(RueckrufService rueckrufService) {
        this.rueckrufService = rueckrufService;
    }

    @GetMapping("/all")
    public List<RueckrufWithProduct> getAllRueckrufe() {
        rueckrufService.printRueckrufWithProduct();
        return rueckrufService.getAllRueckrufe();
    }

    @GetMapping("/filter")
    public List<RueckrufWithProduct> getFilteredRueckrufe(
            @RequestParam(value = "q", required = false) String filterQuery) {
        return rueckrufService.getFilteredRueckrufe(filterQuery);
    }
}
