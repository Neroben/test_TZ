package project.persistence.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")
@Data
public class ProductEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "product_uuid", strategy = "uuid")
    private UUID id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private UserEntity supplier;

    @OneToMany(mappedBy = "product")
    private List<ProductPriceEntity> prices;
}
