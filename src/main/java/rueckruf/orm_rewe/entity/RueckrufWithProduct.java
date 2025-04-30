package rueckruf.orm_rewe.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Setter
@Getter
@Component
public class RueckrufWithProduct {
    private List<Product> productList;
    private Rueckruf rueckruf;
}
