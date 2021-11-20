package project.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.persistence.entity.ProductPriceEntity;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface ProductPriceRepository extends JpaRepository<ProductPriceEntity, BigInteger> {

    @Query("select price from ProductPriceEntity price" +
            " where price.product.id = :productId and price.dateStart < :date and price.dateEnd > :date")
    Optional<ProductPriceEntity> findActualPrice(@Param("productId") UUID product, @Param("date") LocalDateTime date);

    @Transactional
    @Modifying
    @Query("delete from ProductPriceEntity price " +
            "where price IN (select price from UserEntity user " +
            "inner JOIN ProductEntity product on product.supplier = user " +
            "inner JOIN ProductPriceEntity price on price.product = product " +
            "where user.email = :email " +
            "and price.id = :id)")
    void deleteByIdAndUserEmail(@Param("id") BigInteger id, @Param("email") String email);

}
