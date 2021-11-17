package project.persistence.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
public class ProductPriceEntity {

    @Id
    private BigInteger id;

    private BigDecimal price;

    private LocalDate dateStart;

    private LocalDate dateEnd;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

}
