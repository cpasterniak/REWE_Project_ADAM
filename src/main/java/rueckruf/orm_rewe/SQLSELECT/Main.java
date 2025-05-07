package rueckruf.orm_rewe.SQLSELECT;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Abfrage value1 = new Value("rueckruf_id:1");
        Abfrage value2 = new Value("2");
        Abfrage value3 = new Value("3");

        Abfrage add = new ADD(value1, value2);

        Abfrage between = new BETWEEN(value2, value3);

        Abfrage or = new OR(add, between);

        Abfrage select = new SELECT(or);

        System.out.println(select.toString());


        String filterQuery = "rueckruf_id:1,2,3;pm:Toni";

        System.out.println(parseQuery(filterQuery));


    }

    public static String parseQuery(String query) {
        String[] columns = query.split(";");
        Map<String, String[]> filters = new HashMap<>();
        for (String column : columns) {
            filters.put(column.split(":")[0], column.split(":")[1].split(","));
        }
        return simpleSQLQuery(filters);
    }

    public static String simpleSQLQuery(Map<String, String[]> filters) {
        Abfrage v1 = null;
        for(String columns : filters.keySet()) {
            int length = filters.get(columns).length;
            if(filters.get(columns).length > 1) {
                for (int i = 0; i < length; i++) {
                    if(i == 0) {
                        v1 = new Value(columns + " = " + filters.get(columns)[i]);
                    } else {
                        v1 = new OR(v1, new Value(columns + " = " + filters.get(columns)[i]));
                    }
                }
            } else {
                v1 = new ADD(v1, new Value(columns + " = " + filters.get(columns)[0]));
            }
        }
        return v1.toString();
    }
}