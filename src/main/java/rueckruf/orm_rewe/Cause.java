package rueckruf.orm_rewe;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cause {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long causeId;

    private String detailedCause;
    private Integer causeCategory;
    private Integer causeOption;

    private Boolean billa;
    private Boolean billaPlus;
    private Boolean billaKaufleute;
    private Boolean penny;
    private Boolean bipa;
    private Boolean sutterluety;
    private Boolean adeg;
    private Boolean ghKooperation;

}

