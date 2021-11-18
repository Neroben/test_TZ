package project.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.persistence.entity.DeliveryHistoryEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface DeliveryHistoryRepository extends JpaRepository<DeliveryHistoryEntity, UUID> {

    @Query("select h from DeliveryHistoryEntity h where h.date > :dateStart and h.date < :dateEnd")
    List<DeliveryHistoryEntity> findAllByPeriod(@Param("dateStart") LocalDateTime dateStart,
                                                @Param("dateEnd") LocalDateTime dateEnd);

    void deleteByIdAndConsumerId(UUID id, UUID consumerId);

}
