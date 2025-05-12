package rueckruf.orm_rewe.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RueckrufWithProduct {
    private List<Product> productList;
    private Rueckruf rueckruf;

    public RueckrufWithProduct(Rueckruf rueckruf, List<Product> productList) {
        this.rueckruf = rueckruf;
        this.productList = productList;
    }

    public RueckrufWithProduct() {
    }

    @Override
    public boolean equals(Object o) {
        RueckrufWithProduct rwf = (RueckrufWithProduct) o;
        return rueckruf.equals(rwf.getRueckruf());
    }

    @Override
    public int hashCode() {
        return this.rueckruf.hashCode();
    }
}