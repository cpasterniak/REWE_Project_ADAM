package rueckruf.orm_rewe.SQLSELECT;

public class OR implements Abfrage {
    private Abfrage value1;
    private Abfrage value2;

    public OR(Abfrage value1, Abfrage value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public String toString() {
        return value1.toString() + " OR " + value2.toString();
    }
}
