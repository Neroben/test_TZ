package project.persistence.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "delivery_history")
@Data
public class DeliveryHistoryEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "delivery_history_uuid", strategy = "uuid")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private UserEntity consumer;

    private LocalDateTime date;

    @OneToMany(mappedBy = "delivery", cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
    private List<OrderLineEntity> orderLineList;

    @PrePersist
    private void setDate() {
        if (date == null) {
            date = LocalDateTime.now();
        }
    }

}
