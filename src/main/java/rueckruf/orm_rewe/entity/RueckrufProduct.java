package rueckruf.orm_rewe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class RueckrufProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rueckruf_id")
    @JsonBackReference
    private Rueckruf rueckruf;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonManagedReference("product-ref")
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rueckruf getRueckruf() {
        return rueckruf;
    }

    public void setRueckruf(Rueckruf rueckruf) {
        this.rueckruf = rueckruf;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

