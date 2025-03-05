package rueckruf.orm_rewe;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rueckruf")
public class RueckrufController {

    private final RueckrufService rueckrufService;
    private final LieferantenService lieferantenService;

    public RueckrufController(RueckrufService rueckrufService, LieferantenService lieferantenService) {
        this.rueckrufService = rueckrufService;
        this.lieferantenService = lieferantenService;
    }

    @GetMapping
    public List<Rueckruf> getAllRueckrufe() {
        return rueckrufService.getAllRueckrufe();
    }

    @GetMapping("/{id}")
    public @ResponseBody Rueckruf getRueckruf(@PathVariable Long id) {
        return rueckrufService.getRueckrufById(id);
    }

    @PostMapping
    public Rueckruf createRueckruf(@RequestBody Rueckruf rueckruf) {
        return rueckrufService.createRueckruf(rueckruf);
    }

    /**@DeleteMapping("/{id}")
    public void deleteRueckruf(@PathVariable Long id) {
        rueckrufService.deleteRueckruf(id);
    }*/

    @GetMapping("/Hallo")
    public String printHallo() {
        return "Hello World";
    }
}

