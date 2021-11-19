package project.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.persistence.entity.ProductPriceEntity;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface ProductPriceRepository extends JpaRepository<ProductPriceEntity, BigInteger> {

    @Query("select price from ProductPriceEntity price" +
            " where price.product.id = :productId and price.dateStart < :date and price.dateEnd > :date")
    Optional<ProductPriceEntity> findActualPrice(@Param("productId") UUID product, @Param("date") LocalDateTime date);

    @Query("delete from ProductPriceEntity price " +
            "where price IN (select price from UserEntity user " +
            "JOIN ProductEntity product " +
            "JOIN ProductPriceEntity price " +
            "where user.email = :email " +
            "and price.id = :id " +
            "and user.id = product.supplier " +
            "and product.id = price.product)")
    void deleteByIdAndUserEmail(@Param("id") BigInteger id, @Param("email") String email);

}
