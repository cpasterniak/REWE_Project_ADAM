package rueckruf.orm_rewe.Service;

import org.springframework.stereotype.Service;
import rueckruf.orm_rewe.entity.Product;
import rueckruf.orm_rewe.entity.Rueckruf;
import rueckruf.orm_rewe.entity.RueckrufProduct;
import rueckruf.orm_rewe.entity.RueckrufWithProduct;
import rueckruf.orm_rewe.repositories.ProductRepository;
import rueckruf.orm_rewe.repositories.RueckrufRepository;
import rueckruf.orm_rewe.repositories.RueckrufProductRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RueckrufService {
    private RueckrufRepository repo;
    private ProductRepository productRepo;
    private RueckrufProductRepository rueckrufProductRepo;
    private List<RueckrufWithProduct> rueckrufWithProduct;

    public RueckrufService(RueckrufRepository repo, ProductRepository productRepo, RueckrufProductRepository rueckrufProductRepo) {
        this.repo = repo;
        this.productRepo = productRepo;
        this.rueckrufProductRepo = rueckrufProductRepo;
        JoinRueckrufProduct();
    }

    public void JoinRueckrufProduct() {
        List<RueckrufWithProduct> rueckrufe = new ArrayList<>();
        Map<Rueckruf, List<Product>> listMap = new HashMap<>();
        for (RueckrufProduct p : rueckrufProductRepo.findAll()) {
            listMap.computeIfAbsent(p.getRueckruf(),k -> new ArrayList<>()).add(p.getProduct());
        }
        for (Rueckruf r : listMap.keySet()) {
            RueckrufWithProduct rwp = new RueckrufWithProduct();
            rwp.setProductList(listMap.get(r));
            rwp.setRueckruf(r);
            rueckrufe.add(rwp);
        }
        this.rueckrufWithProduct = rueckrufe;
    }

    public List<RueckrufWithProduct> getAllRueckrufe() {
        return this.rueckrufWithProduct;
    }

    public void printRueckrufWithProduct() {
        for (RueckrufWithProduct rwp : rueckrufWithProduct) {
            System.out.println(rwp.getRueckruf().getArt());
        }
    }

    public List<RueckrufWithProduct> getFilteredRueckrufe(String filterQuery) {
        if (filterQuery == null || filterQuery.trim().isEmpty()) {
            return getAllRueckrufe();
        }

        // Parse the filter query
        Map<String, List<String>> filterMap = parseFilterQuery(filterQuery);

        // Apply filters
        return rueckrufWithProduct.stream()
                .filter(rwp -> matchesAllFilters(rwp, filterMap))
                .collect(Collectors.toList());
    }

    private Map<String, List<String>> parseFilterQuery(String filterQuery) {
        Map<String, List<String>> filterMap = new HashMap<>();

        String[] columnFilters = filterQuery.split(";");
        for (String columnFilter : columnFilters) {
            String[] parts = columnFilter.split(":", 2);
            if (parts.length == 2) {
                String column = parts[0].trim();
                String[] values = parts[1].split(",");
                filterMap.put(column, Arrays.stream(values)
                        .map(String::trim)
                        .collect(Collectors.toList()));
            }
        }

        return filterMap;
    }

    private boolean matchesAllFilters(RueckrufWithProduct rwp, Map<String, List<String>> filterMap) {
        Rueckruf rueckruf = rwp.getRueckruf();
        List<Product> products = rwp.getProductList();

        for (Map.Entry<String, List<String>> entry : filterMap.entrySet()) {
            String column = entry.getKey();
            List<String> values = entry.getValue();

            if (!matchesAnyValue(column, values, rueckruf, products)) {
                return false;
            }
        }

        return true;
    }

    private boolean matchesAnyValue(String column, List<String> values, Rueckruf rueckruf, List<Product> products) {
        for (String value : values) {
            if (matchesValue(column, value, rueckruf, products)) {
                return true;
            }
        }
        return false;
    }

    private boolean matchesValue(String column, String value, Rueckruf rueckruf, List<Product> products) {
        // Handle range values (with -)
        if (value.contains("-")) {
            String[] range = value.split("-");
            if (range.length == 2) {
                return matchesRange(column, range[0].trim(), range[1].trim(), rueckruf, products);
            }
        }

        // Handle exact match
        return matchesExact(column, value, rueckruf, products);
    }

    private boolean matchesRange(String column, String from, String to, Rueckruf rueckruf, List<Product> products) {
        try {
            switch (column.toLowerCase()) {
                case "id":
                case "rueckrufid":
                    long idFrom = Long.parseLong(from);
                    long idTo = Long.parseLong(to);
                    long id = rueckruf.getRueckrufId();
                    return id >= idFrom && id <= idTo;
                case "year":
                    int yearFrom = Integer.parseInt(from);
                    int yearTo = Integer.parseInt(to);
                    int year = rueckruf.getYear();
                    return year >= yearFrom && year <= yearTo;
                case "halfyear":
                    int halfYearFrom = Integer.parseInt(from);
                    int halfYearTo = Integer.parseInt(to);
                    int halfYear = rueckruf.getHalfyear();
                    return halfYear >= halfYearFrom && halfYear <= halfYearTo;
                case "month":
                    int monthFrom = Integer.parseInt(from);
                    int monthTo = Integer.parseInt(to);
                    int month = rueckruf.getMonth();
                    return month >= monthFrom && month <= monthTo;
                case "prueNumber":
                    int prueFrom = Integer.parseInt(from);
                    int prueTo = Integer.parseInt(to);
                    int prue = rueckruf.getPrueNumber();
                    return prue >= prueFrom && prue <= prueTo;
                case "prue_freigabe":
                    LocalDate prueFreigabeFrom = LocalDate.parse(from);
                    LocalDate prueFreigabeTo = LocalDate.parse(to);
                    LocalDate prueFreigabe = rueckruf.getPrueFreigabe();
                    return prueFreigabe.isBefore(prueFreigabeFrom) && prueFreigabe <= prueTo;
                case "artikelanzahlprue":
                    int artikelFrom = Integer.parseInt(from);
                    int artikelTo = Integer.parseInt(to);
                    int artikel = rueckruf.getArtikelanzahlPrue();
                    return artikel >= artikelFrom && artikel <= artikelTo;
                case "note":
                    int noteFrom = Integer.parseInt(from);
                    int noteTo = Integer.parseInt(to);
                    int note = rueckruf.getNote();
                    return note >= noteFrom && note <= noteTo;
                case "warengruppe":
                    return products.stream().anyMatch(p -> {
                        int wg = p.getWarengruppe();
                        int wgFrom = Integer.parseInt(from);
                        int wgTo = Integer.parseInt(to);
                        return wg >= wgFrom && wg <= wgTo;
                    });
                // Add more numeric fields as needed
                default:
                    return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean matchesExact(String column, String value, Rueckruf rueckruf, List<Product> products) {
        switch (column.toLowerCase()) {
            case "id":
            case "rueckrufid":
                return String.valueOf(rueckruf.getRueckrufId()).equalsIgnoreCase(value);
            case "art":
                return rueckruf.getArt() != null && rueckruf.getArt().equalsIgnoreCase(value);
            case "type":
                return rueckruf.getType() != null && rueckruf.getType().equalsIgnoreCase(value);
            case "status":
                return rueckruf.getStatus() != null && rueckruf.getStatus().equalsIgnoreCase(value);
            case "marke":
            case "ismarke":
                return rueckruf.getMarke() != null &&
                        rueckruf.getMarke().toString().equalsIgnoreCase(value);
            case "food":
                return products.stream().anyMatch(p ->
                        p.getFood() != null && p.getFood().toString().equalsIgnoreCase(value));
            case "warengruppe":
                return products.stream().anyMatch(p ->
                        p.getWarengruppe() != null &&
                                String.valueOf(p.getWarengruppe()).equalsIgnoreCase(value));
            case "ean":
                return products.stream().anyMatch(p ->
                        p.getEan() != null &&
                                String.valueOf(p.getEan()).equalsIgnoreCase(value));
            case "productname":
            case "productbezeichnung":
                return products.stream().anyMatch(p ->
                        p.getProductBezeichnung() != null &&
                                p.getProductBezeichnung().equalsIgnoreCase(value));
            case "markenname":
            case "productbezeichnungmarkenname":
                return products.stream().anyMatch(p ->
                        p.getProductBezeichnungMarkenname() != null &&
                                p.getProductBezeichnungMarkenname().equalsIgnoreCase(value));
            case ""
            // Add more fields as needed
            default:
                return false;
        }
    }
}

