/**package rueckruf.orm_rewe.Service;

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
            RueckrufWithProduct rwp = new RueckrufWithProduct(r, listMap.get(r));
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
                case "pruenumber":
                    int prueFrom = Integer.parseInt(from);
                    int prueTo = Integer.parseInt(to);
                    int prue = rueckruf.getPrueNumber();
                    return prue >= prueFrom && prue <= prueTo;
                case "prue_freigabe":
                    LocalDate prueFreigabeFrom = LocalDate.parse(from);
                    LocalDate prueFreigabeTo = LocalDate.parse(to);
                    LocalDate prueFreigabe = rueckruf.getPrueFreigabe();
                    return prueFreigabe.isAfter(prueFreigabeFrom) && prueFreigabe.isBefore(prueFreigabeTo);
                case "pi_income_date":
                    LocalDate piIncomeDateFrom = LocalDate.parse(from);
                    LocalDate piIncomeDateTo = LocalDate.parse(to);
                    LocalDate piIncomeDate = rueckruf.getPiIncomeDate();
                    return piIncomeDate.isAfter(piIncomeDateFrom) && piIncomeDate.isBefore(piIncomeDateTo);
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
            case "day":
                return rueckruf.getDay() != null && rueckruf.getDay().equalsIgnoreCase(value);
            case "meldung":
                return rueckruf.getMeldung() != null &&
                        rueckruf.getMeldung().toString().equalsIgnoreCase(value);

            case "formular":
                return rueckruf.getFormular() != null &&
                        rueckruf.getFormular().toString().equalsIgnoreCase(value);

            case "cm":
            case "qm":
            case "bm":
            case "pm":
                String person = column.equals("cm") ? rueckruf.getCm() :
                        column.equals("qm") ? rueckruf.getQm() :
                                column.equals("bm") ? rueckruf.getBm() : rueckruf.getPm();
                return person != null && person.equalsIgnoreCase(value);

            case "mandatar":
                return rueckruf.getMandatar() != null &&
                        rueckruf.getMandatar().equalsIgnoreCase(value);

            case "rechnung_sprache":
                return rueckruf.getRechnungSprache() != null &&
                        rueckruf.getRechnungSprache().equalsIgnoreCase(value);

            case "rechnung_kontrolle":
                return rueckruf.getRechnungKontrolle() != null &&
                        rueckruf.getRechnungKontrolle().equalsIgnoreCase(value);
            // Felder aus Lieferant
            case "lieferant":
            case "lieferantenname":
                return rueckruf.getLieferant() != null &&
                        rueckruf.getLieferant().getLieferantenname() != null &&
                        rueckruf.getLieferant().getLieferantenname().equalsIgnoreCase(value);

            case "lieferantadress":
                return rueckruf.getLieferant() != null &&
                        rueckruf.getLieferant().getLieferantadress() != null &&
                        rueckruf.getLieferant().getLieferantadress().equalsIgnoreCase(value);

            // Felder aus Produzent
            case "produzent":
            case "produzentname":
                return rueckruf.getProduzent() != null &&
                        rueckruf.getProduzent().getProduzentname() != null &&
                        rueckruf.getProduzent().getProduzentname().equalsIgnoreCase(value);

            case "produzentadress":
                return rueckruf.getProduzent() != null &&
                        rueckruf.getProduzent().getProduzentadress() != null &&
                        rueckruf.getProduzent().getProduzentadress().equalsIgnoreCase(value);

            // Felder aus Cause
            case "cause_category":
                return rueckruf.getCause() != null &&
                        String.valueOf(rueckruf.getCause().getCauseCategory()).equalsIgnoreCase(value);

            case "cause_option":
                return rueckruf.getCause() != null &&
                        String.valueOf(rueckruf.getCause().getCauseOption()).equalsIgnoreCase(value);

            case "detailed_cause":
                return rueckruf.getCause() != null &&
                        rueckruf.getCause().getDetailedCause() != null &&
                        rueckruf.getCause().getDetailedCause().equalsIgnoreCase(value);

            case "billa":
            case "billa_plus":
            case "billa_kaufleute":
            case "penny":
            case "bipa":
            case "sutterluety":
            case "adeg":
            case "ghkooperation":
                if (rueckruf.getCause() == null) return false;
                Boolean flagValue = switch (column.toLowerCase()) {
                    case "billa" -> rueckruf.getCause().getBilla();
                    case "billa_plus" -> rueckruf.getCause().getBillaPlus();
                    case "billa_kaufleute" -> rueckruf.getCause().getBillaKaufleute();
                    case "penny" -> rueckruf.getCause().getPenny();
                    case "bipa" -> rueckruf.getCause().getBipa();
                    case "sutterluety" -> rueckruf.getCause().getSutterluety();
                    case "adeg" -> rueckruf.getCause().getAdeg();
                    default -> rueckruf.getCause().getGhKooperation();
                };
                return flagValue != null && flagValue.toString().equalsIgnoreCase(value);

            // Felder aus Product
            case "specific_articlenumber":
                return products.stream().anyMatch(p ->
                        p.getSpecificArticlenumber() != null &&
                                p.getSpecificArticlenumber().equalsIgnoreCase(value));

            case "mhd_charge":
                return products.stream().anyMatch(p ->
                        p.getMhdCharge() != null &&
                                p.getMhdCharge().equalsIgnoreCase(value));

            case "not_product_bezeichnung":
                return products.stream().anyMatch(p ->
                        p.getNotProductBezeichnung() != null &&
                                p.getNotProductBezeichnung().equalsIgnoreCase(value));

            case "not_product_bezeichnung_markenname":
                return products.stream().anyMatch(p ->
                        p.getNotProductBezeichnungMarkenname() != null &&
                                p.getNotProductBezeichnungMarkenname().equalsIgnoreCase(value));

            case "not_product_bezeichnung_grammatur":
                return products.stream().anyMatch(p ->
                        p.getNotProductBezeichnungGrammatur() != null &&
                                p.getNotProductBezeichnungGrammatur().equalsIgnoreCase(value));

            default:
                return false;
        }
    }
}*/

