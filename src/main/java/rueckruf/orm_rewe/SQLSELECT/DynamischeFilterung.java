package rueckruf.orm_rewe.SQLSELECT;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
@Getter
@Setter
public class DynamischeFilterung {
    private String query;

    public DynamischeFilterung(String filter) {
        this.query = filter;
        Abfrage ergebnis = parseQuery(filter);
        this.query = new SELECT(ergebnis).toString();
    }

    public Abfrage parseQuery(String query) {
        String[] columns = query.split(";");
        Map<String, String[]> filters = new HashMap<>();
        for (String column : columns) {
            filters.put(column.split(":")[0], column.split(":")[1].split(","));
        }
        return simpleSQLQuery(filters);
    }

    public Abfrage simpleSQLQuery(Map<String, String[]> filters) {
        Abfrage v1 = null;
        if(filters.keySet().size() == 1) {return new Value(filters.keySet().iterator().next() + " = '" + filters.get(filters.keySet().iterator().next())[0] + "'");}
        for(String columns : filters.keySet()) {
            int length = filters.get(columns).length;
            if(filters.get(columns).length > 1) {
                for (int i = 0; i < length; i++) {
                    if(i == 0) {
                        v1 = new Value(columns + " = '" + filters.get(columns)[i] + "'");
                    } else {
                        v1 = new OR(v1, new Value(columns + " = '" + filters.get(columns)[i] + "'"));
                    }
                }
            } else {
                v1 = new ADD(v1, new Value(columns + " = '" + filters.get(columns)[0] + "'"));
            }
        }
        return v1;
    }
}
