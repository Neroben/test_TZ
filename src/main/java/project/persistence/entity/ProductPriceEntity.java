package project.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_prices")
@Data
public class ProductPriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    private BigDecimal price;

    private LocalDateTime dateStart;

    private LocalDateTime dateEnd;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

}
