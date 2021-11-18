package project.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.persistence.entity.ProductEntity;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    List<ProductEntity> findBySupplierId(UUID id);

    void deleteByIdAndSupplierId(UUID id, UUID supplierId);

}
