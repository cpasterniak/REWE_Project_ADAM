package rueckruf.orm_rewe.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
