package rueckruf.orm_rewe;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LieferantenService {

    private final LieferantRepository lieferantRepository;

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
