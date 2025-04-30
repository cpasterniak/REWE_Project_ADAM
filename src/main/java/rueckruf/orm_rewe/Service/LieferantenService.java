package rueckruf.orm_rewe.Service;

import org.springframework.stereotype.Service;
import rueckruf.orm_rewe.entity.Lieferant;
import rueckruf.orm_rewe.repositories.LieferantRepository;

import java.util.List;

@Service
public class LieferantenService {

    private final rueckruf.orm_rewe.repositories.LieferantRepository lieferantRepository;

    public LieferantenService(LieferantRepository lieferantRepository) {
        this.lieferantRepository = lieferantRepository;
    }

    public List<Lieferant> getAllRueckrufe() {
        return lieferantRepository.findAll();
    }

    public Lieferant getLieferantById(Long id) {
        return lieferantRepository.findById(id).orElse(null);
    }

    public Lieferant createRueckruf(Lieferant rueckruf) {
        return lieferantRepository.save(rueckruf);
    }

    public void deleteRueckruf(Long id) {
        lieferantRepository.deleteById(id);
    }
}
