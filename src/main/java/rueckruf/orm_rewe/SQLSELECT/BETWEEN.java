package rueckruf.orm_rewe.SQLSELECT;

public class BETWEEN implements Abfrage {
    private Abfrage value1;
    private Abfrage value2;

    public BETWEEN(Abfrage value1, Abfrage value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    @Override
    public String toString() {
        return " BETWEEN " + value1.toString() + " AND " + value2.toString();
    }
}
