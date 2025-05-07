package rueckruf.orm_rewe.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productBezeichnungMarkenname;
    private String productBezeichnung;
    private Integer productBezeichnungGrammatur;

    private String notProductBezeichnungMarkenname;
    private String notProductBezeichnung;
    private String notProductBezeichnungGrammatur;

    private String specificArticlenumber;
    private Long ean;
    private String mhdCharge;

    private Integer warengruppe;
    private Boolean food;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference("product-ref")
    private List<RueckrufProduct> rueckrufe = new ArrayList<>();

}




