package project.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.persistence.entity.ProductEntity;
import project.persistence.entity.ProductPriceEntity;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;

public interface ProductPriceRepository extends JpaRepository<ProductPriceEntity, BigInteger> {

    @Query("select price from ProductPriceEntity price where price.product = :product and price.dateStart < :date and price.dateEnd > :date")
    Optional<ProductPriceEntity> findActualPrice(@Param("product")ProductEntity product, @Param("date") LocalDateTime date);

//    @Query("select ")
//    Optional<ProductPriceEntity> findByDateInPeriod(LocalDateTime dateTime);

}
