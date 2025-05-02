package rueckruf.orm_rewe;

import org.springframework.web.bind.annotation.*;

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
        return rueckrufServiceSQL.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public RueckrufWithProduct getRueckrufById(@PathVariable Long id) {
        return rueckrufServiceSQL.findById(id);
    }

    @GetMapping("/filter")
    public List<Rueckruf> getRueckrufByFilter(@RequestParam String filter) {
        return rueckrufServiceSQL.findByFilter(filter);
    }
}
