package project.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

@Entity
@Table(name = "order_line")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderLineEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "order_line_uuid", strategy = "uuid")
    private UUID id;

    private BigDecimal price;

    private BigInteger weight;

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private DeliveryHistoryEntity delivery;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

}
