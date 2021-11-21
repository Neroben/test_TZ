package project.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.persistence.entity.*;
import project.persistence.repository.*;
import project.security.GrantedAuthorityEnum;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Transactional
public abstract class AbstractIntegrationTest {

    public static final String CONSUMER = "consumer";

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected DeliveryHistoryRepository deliveryHistoryRepository;

    @Autowired
    protected ProductRepository productRepository;

    @Autowired
    protected OrderLineRepository orderLineRepository;

    @Autowired
    protected ProductPriceRepository productPriceRepository;


    protected ProductEntity initProductEntity(String userEmail) {
        ProductEntity product = new ProductEntity();
        product.setName("product");
        product.setSupplier(initUserEntity(userEmail, GrantedAuthorityEnum.SUPPLIER));
        return productRepository.save(product);
    }

    protected UserEntity initUserEntity(String email, GrantedAuthorityEnum authority) {
        UserEntity consumer = new UserEntity();
        consumer.setEmail(email);
        consumer.setPassword("test");
        consumer.setAuthority(authority);
        return userRepository.save(consumer);
    }

    protected OrderLineEntity initOrderLineEntity(String userEmail) {
        OrderLineEntity orderLineEntity = new OrderLineEntity();
        orderLineEntity.setProduct(initProductEntity(userEmail));
        orderLineEntity.setPrice(BigDecimal.valueOf(50L));
        orderLineEntity.setWeight(BigInteger.valueOf(101L));
        return orderLineRepository.save(orderLineEntity);
    }

    protected DeliveryHistoryEntity initDeliveryHistoryEntity(List<OrderLineEntity> orderLines) {
        DeliveryHistoryEntity deliveryHistoryEntity = new DeliveryHistoryEntity();
        deliveryHistoryEntity.setDate(LocalDateTime.now());
        deliveryHistoryEntity.setOrderLineList(orderLines);
        deliveryHistoryEntity.setConsumer(initUserEntity("consumer", GrantedAuthorityEnum.CONSUMER));
        return deliveryHistoryRepository.save(deliveryHistoryEntity);
    }

    protected ProductPriceEntity initProductPriceEntity(ProductEntity product) {
        ProductPriceEntity productPriceEntity = new ProductPriceEntity();
        productPriceEntity.setProduct(product);
        productPriceEntity.setPrice(BigDecimal.valueOf(100L));
        productPriceEntity.setDateStart(LocalDateTime.now().minusHours(1L));
        productPriceEntity.setDateEnd(LocalDateTime.now().plusHours(1L));
        return productPriceRepository.save(productPriceEntity);
    }

    protected void deleteAll() {
        orderLineRepository.deleteAll();
        productPriceRepository.deleteAll();
        productRepository.deleteAll();
        deliveryHistoryRepository.deleteAll();
        userRepository.deleteAll();
    }

}
