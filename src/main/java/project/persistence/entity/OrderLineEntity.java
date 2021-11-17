package project.persistence.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.UUID;

@Entity
@Data
public class OrderLineEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "order_line_uuid", strategy = "uuid")
    private UUID id;

    private BigInteger price;

    private BigInteger weight;

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private DeliveryHistoryEntity delivery;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

}
