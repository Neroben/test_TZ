package project.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.persistence.entity.OrderLineEntity;

import java.util.UUID;

public interface OrderLineRepository extends JpaRepository<OrderLineEntity, UUID> {
}
