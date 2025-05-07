package rueckruf.orm_rewe.SQLSELECT;

public class SELECT implements Abfrage {
    private Abfrage value;

    public SELECT(Abfrage value) {
        this.value = value;
    }

    public String toString() {
        return "SELECT * FROM rueckruf_product JOIN rueckruf r ON rp.rueckruf_id = r.rueckruf_id JOIN product p ON rp.product_id = p.product_id JOIN lieferant l ON r.lieferant_id = l.lieferant_id JOIN versioncontrol v ON r.rueckruf_id = v.rueckruf_id JOIN cause c ON c.cause_id = r.cause_id JOIN produzent pz ON pz.produzent_id = r.produzent_id WHERE " + value.toString();
    }
}
