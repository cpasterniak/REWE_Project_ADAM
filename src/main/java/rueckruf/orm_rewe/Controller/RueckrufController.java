package rueckruf.orm_rewe.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import rueckruf.orm_rewe.Service.RueckrufServiceSQL;
import rueckruf.orm_rewe.entity.RueckrufWithProduct;

import java.util.List;

@RestController
@RequestMapping("/rueckruf")
public class RueckrufController {
    private final RueckrufServiceSQL rueckrufServiceSQL;

    public RueckrufController(RueckrufServiceSQL rueckrufServiceSQL) {
        this.rueckrufServiceSQL = rueckrufServiceSQL;
    }

    @GetMapping("/all")
    @ResponseBody
    public List<RueckrufWithProduct> getAllRueckrufe() {
        return rueckrufServiceSQL.getAllRueckrufeWithProducts();
    }

}
