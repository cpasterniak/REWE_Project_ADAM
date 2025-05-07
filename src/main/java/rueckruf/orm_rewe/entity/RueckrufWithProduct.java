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
}
