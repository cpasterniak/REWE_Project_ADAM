package rueckruf.orm_rewe.SQLSELECT;

public class Value implements Abfrage {
    private String value;

    public Value(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
