package rueckruf.orm_rewe;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
}

