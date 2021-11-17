package project.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "product_prices")
@Data
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
