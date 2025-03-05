package rueckruf.orm_rewe;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RueckrufService {
    private final RueckrufRepository rueckrufRepository;

    public RueckrufService(RueckrufRepository rueckrufRepository) {
        this.rueckrufRepository = rueckrufRepository;
    }

    public List<Rueckruf> getAllRueckrufe() {
        return rueckrufRepository.findAll();
    }

    public Rueckruf getRueckrufById(Long id) {
        return rueckrufRepository.findById(id).orElse(null);
    }

    public Rueckruf createRueckruf(Rueckruf rueckruf) {
        return rueckrufRepository.save(rueckruf);
    }

    public void deleteRueckruf(Long id) {
        rueckrufRepository.deleteById(id);
    }
}

