package project.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.persistence.entity.ProductEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    List<ProductEntity> findBySupplierId(UUID id);

    void deleteByIdAndSupplierId(UUID id, UUID supplierId);

    @Query("select product from ProductEntity product join UserEntity user on product.supplier = user" +
            " where product.id = :id and user.email = :email")
    Optional<ProductEntity> findByIdAndUserEmail(@Param("id") UUID id, @Param("email") String email);

}
