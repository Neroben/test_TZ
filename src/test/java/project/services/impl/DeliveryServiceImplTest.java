package project.services.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.errorhandling.exception.OrderLinePermissionException;
import project.persistence.entity.DeliveryHistoryEntity;
import project.persistence.entity.OrderLineEntity;
import project.persistence.entity.ProductEntity;
import project.security.GrantedAuthorityEnum;
import project.services.DeliveryService;
import project.services.dto.AcceptDeliveryDto;
import project.services.dto.CreateOrderLineDto;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryServiceImplTest extends AbstractIntegrationTest {

    @Autowired
    private DeliveryService deliveryService;

    private UUID acceptProductId;
    private UUID someUserProductId;

    @BeforeEach
    void setUp() {
        ProductEntity product = initProductEntity(CONSUMER);
        acceptProductId = product.getId();
        initProductPriceEntity(product);
        someUserProductId = initProductEntity("user").getId();
    }

    @Test
    void acceptDelivery() {
        deliveryService.acceptDelivery(initAcceptDeliveryDto(), CONSUMER);
        List<DeliveryHistoryEntity> allByPeriod = deliveryHistoryRepository.findAllByPeriod(
                LocalDateTime.now().minusMinutes(1L),
                LocalDateTime.now().plusMinutes(1L));
        assertEquals(1, allByPeriod.size());
    }

    @Test
    void throwOrderLinePermissionExceptionWhenAcceptDelivery() {
        AcceptDeliveryDto acceptDeliveryDto = initAcceptDeliveryDto();
        acceptDeliveryDto.setOrderLines(List.of(
                initCreateOrderLineDto(acceptProductId),
                initCreateOrderLineDto(someUserProductId)
        ));
        assertThrows(OrderLinePermissionException.class,
                () -> deliveryService.acceptDelivery(acceptDeliveryDto, CONSUMER));
    }

    private AcceptDeliveryDto initAcceptDeliveryDto() {
        AcceptDeliveryDto acceptDeliveryDto = new AcceptDeliveryDto();
        acceptDeliveryDto.setOrderLines(
                List.of(initCreateOrderLineDto(acceptProductId)));
        return acceptDeliveryDto;
    }

    private CreateOrderLineDto initCreateOrderLineDto(UUID productId) {
        CreateOrderLineDto createOrderLineDto = new CreateOrderLineDto();
        createOrderLineDto.setProductId(productId);
        createOrderLineDto.setWeight(BigInteger.valueOf(999L));
        return createOrderLineDto;
    }

    @Test
    void deleteDelivery() {
        OrderLineEntity orderLine = initOrderLineEntity(CONSUMER);
        DeliveryHistoryEntity deliveryHistoryEntity = initDeliveryHistoryEntity(List.of(orderLine));
        assertTrue(deliveryHistoryRepository.findById(deliveryHistoryEntity.getId()).isPresent());
        assertTrue(orderLineRepository.findById(orderLine.getId()).isPresent());
        deliveryService.deleteDelivery(deliveryHistoryEntity.getId());
        assertTrue(deliveryHistoryRepository.findById(deliveryHistoryEntity.getId()).isEmpty());
        assertTrue(orderLineRepository.findById(orderLine.getId()).isEmpty());
    }
}