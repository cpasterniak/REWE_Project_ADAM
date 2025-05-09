package rueckruf.orm_rewe.Service;
/**
import org.springframework.stereotype.Service;
import rueckruf.orm_rewe.entity.Product;
import rueckruf.orm_rewe.entity.Rueckruf;
import rueckruf.orm_rewe.entity.RueckrufProduct;
import rueckruf.orm_rewe.entity.RueckrufWithProduct;
import rueckruf.orm_rewe.repository.ProductRepository;
import rueckruf.orm_rewe.repository.RueckrufRepository;
import rueckruf.orm_rewe.repository.RueckrufProductRepository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Service
public class RueckrufService {
    private final RueckrufProductRepository rueckrufProductRepository;
    private static RueckrufRepository repo;
    private ProductRepository productRepo;
    private RueckrufProductRepository rueckrufProductRepo;
    private List<RueckrufWithProduct> rueckrufWithProduct;

    public RueckrufService(RueckrufRepository repo, ProductRepository productRepo, RueckrufProductRepository rueckrufProductRepo, RueckrufProductRepository rueckrufProductRepository) {
        this.repo = repo;
        this.productRepo = productRepo;
        this.rueckrufProductRepo = rueckrufProductRepo;
        JoinRueckrufProduct();
        this.rueckrufProductRepository = rueckrufProductRepository;
    }

    public void JoinRueckrufProduct() {
        List<RueckrufWithProduct> rueckrufe = new ArrayList<>();
        Map<Rueckruf, List<Product>> listMap = new HashMap<>();
        for (RueckrufProduct p : rueckrufProductRepo.findAll()) {
            listMap.computeIfAbsent(p.getRueckruf(),k -> new ArrayList<>()).add(p.getProduct());
        }
        for (Rueckruf r : listMap.keySet()) {
            RueckrufWithProduct rwp = new RueckrufWithProduct(r, listMap.get(r));
            rueckrufe.add(rwp);
        }
        this.rueckrufWithProduct = rueckrufe;
    }

    public List<RueckrufWithProduct> getAllRueckrufe() {
        return this.rueckrufWithProduct;
    }

    public List<RueckrufWithProduct> filterRueckruf(String filter) {

        return rueckrufWithProduct;
    }

    public Map<String, String[]> parsingQuery(String query) {
        String[] columns = query.split(";");
        Map<String, String[]> filters = new HashMap<>();
        for (String column : columns) {
            filters.put(column.split(":")[0], column.split(":")[1].split(","));
        }
        return filters;
    }

    public void printRueckrufWithProduct() {
        for (RueckrufWithProduct rwp : rueckrufWithProduct) {
            System.out.println(rwp.getRueckruf().getArt());
        }
    }
}*/


