package project.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.persistence.entity.DeliveryHistoryEntity;

import java.util.UUID;

public interface DeliveryHistoryRepository extends JpaRepository<DeliveryHistoryEntity, UUID> {
}
