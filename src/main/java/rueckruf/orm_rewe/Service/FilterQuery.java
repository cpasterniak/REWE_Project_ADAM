package rueckruf.orm_rewe.Service;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterQuery {
    @NotNull
    private final String filterQuery;
    Map<String, String[]> filters;
    List<String> rueckrufColumns = List.of(
            "rueckruf_id", "year", "halfyear", "month", "day", "prue_number", "prue_freigabe",
            "art", "artdate", "pi_income_date", "type", "is_marke", "artikelanzahl_prue",
            "kassasperre", "wiederanlieferung", "createkundeninfo", "meldung", "meldung_date",
            "cm", "qm", "bm", "pm", "bearbeitung_beginn", "bearbeitung_ende",
            "artsperr", "anzahl_artsperr", "formular", "mandatar", "note", "cause_id",
            "lieferant_id", "produzent_id", "rechnung_name", "rechnung_betrag",
            "rechnung_sprache", "rechnung_ver_betrag", "rechnung_kontrolle", "status"
    );
    List<String> versioncontrolColumns = List.of(
            "version_id",
            "version_date",
            "description",
            "rueckruf_id"
    );
    List<String> productColumns = List.of(
            "product_id",
            "product_bezeichnung_markenname",
            "product_bezeichnung",
            "product_bezeichnung_grammatur",
            "not_product_bezeichnung_markenname",
            "not_product_bezeichnung",
            "not_product_bezeichnung_grammatur",
            "specific_articlenumber",
            "EAN",
            "MHD_charge",
            "warengruppe",
            "food"
    );
    List<String> causeColumns = List.of(
            "cause_id",
            "detailed_cause",
            "cause_category",
            "cause_option",
            "billa",
            "billa_plus",
            "billa_kaufleute",
            "penny",
            "bipa",
            "sutterluety",
            "adeg",
            "ghKooperation"
    );
    List<String> produzentColumns = List.of(
            "produzent_id",
            "produzentname",
            "produzentadress"
    );
    List<String> lieferantColumns = List.of(
            "lieferant_id",
            "lieferantenname",
            "lieferantadress"
    );

    public FilterQuery(@NotNull String filterQuery) {
        this.filterQuery = filterQuery;
        parsingFilterQuery();
        System.out.println(SQLFilterQuerySingleTable());
    }

    public void parsingFilterQuery() {
        String[] columns = filterQuery.split(";");
        filters = new HashMap<>();
        for (String column : columns) {
            filters.put(column.split(":")[0], column.split(":")[1].split(","));
        }
    }

    public String SQLAddingValue(String value1, String value2) {
        return value1 + " AND " + value2;
    }

    public String SQLOrValue(String value1, String value2) {
        return value1 + " OR " + value2;
    }

    public String SQLBetweenValue(String value1, String value2) {
        return value1 + " BETWEEN " + value2.split("-")[0] + " AND " + value2.split("-")[1];
    }

    public String SQLFilterQuerySingleTable() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM ");
        for (String column : filters.keySet()) {

        }
        return query.toString();
    }

    /**
    public String SQLFilterQuerySingleTable() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM rueckruf WHERE ");
        for (String key : filters.keySet()) {
            int i = 1;
            for (String value : filters.get(key)) {
                System.out.println(filters.get(key).length);
                System.out.println(filters.size());
                if(value.contains("-")) {
                    System.out.println(value);
                    query.append("BETWEEN ").append(value.split("-")[0]).append(" AND ").append(value.split("-")[1]);
                } else if(i < filters.get(key).length) query.append(key).append(" = '").append(value).append("' OR ");
                else if(filters.get(key).length >= filters.size()) query.append(key).append(" = '").append(value).append("' AND ");
                else query.append(key).append(" = '").append(value).append("';");
                i++;
            }
        }
        return query.toString();
    }
     */

    public void checkingColumns() {
        for (String column : filters.keySet()) {
            rueckrufColumns.contains(column);
        }
    }

    public static void main(String[] args) {
        String filterQuery = "id:10,20-30;pm:Toni";
        System.out.println("Start");
        FilterQuery fl = new FilterQuery(filterQuery);
        fl.parsingFilterQuery();
    }



}

