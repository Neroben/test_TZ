package project.services.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.errorhandling.exception.ActualPriceNotFoundException;
import project.errorhandling.exception.OrderLinePermissionException;
import project.persistence.entity.DeliveryHistoryEntity;
import project.persistence.repository.DeliveryHistoryRepository;
import project.persistence.repository.OrderLineRepository;
import project.services.DeliveryService;
import project.services.dto.AcceptDeliveryDto;
import project.services.dto.CreateOrderLineDto;
import project.services.dto.DeliveryHistoryDto;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeliveryServiceImplTest {

    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private DeliveryHistoryRepository deliveryHistoryRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Test
    void acceptDelivery() {
        deliveryService.acceptDelivery(initAcceptDeliveryDto(), "consumer");
        List<DeliveryHistoryEntity> allByPeriod = deliveryHistoryRepository.findAllByPeriod(
                        LocalDateTime.now().minusMinutes(1L),
                        LocalDateTime.now().plusMinutes(1L));
        assertEquals(1, allByPeriod.size());
    }

    @Test
    void throwActualPriceNotFoundExceptionWhenAcceptDelivery() {
        AcceptDeliveryDto acceptDeliveryDto = initAcceptDeliveryDto();
        acceptDeliveryDto.setOrderLines(List.of(
                initCreateOrderLineDto(UUID.fromString("56ac0c06-4721-32ec-81d3-0242ac130005")),
                initCreateOrderLineDto(UUID.fromString("51ac0c06-4721-11ec-81d3-0242ac130005"))
        ));
        assertThrows(ActualPriceNotFoundException.class,
                () -> deliveryService.acceptDelivery(acceptDeliveryDto, "testDelivery"));
    }

    @Test
    void throwOrderLinePermissionExceptionWhenAcceptDelivery() {
        AcceptDeliveryDto acceptDeliveryDto = initAcceptDeliveryDto();
        acceptDeliveryDto.setOrderLines(List.of(
                initCreateOrderLineDto(UUID.fromString("51ac0c06-4721-32ec-81d3-0242ac130005")),
                initCreateOrderLineDto(UUID.fromString("51ac0c06-4721-11ec-81d3-0242ac130005"))
        ));
        assertThrows(OrderLinePermissionException.class,
                () -> deliveryService.acceptDelivery(acceptDeliveryDto, "testDelivery"));
    }

    private AcceptDeliveryDto initAcceptDeliveryDto() {
        AcceptDeliveryDto acceptDeliveryDto = new AcceptDeliveryDto();
        acceptDeliveryDto.setOrderLines(
                List.of(initCreateOrderLineDto(UUID.fromString("51ac0c06-4721-11ec-81d3-0242ac130005"))));
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
        UUID uuid = UUID.fromString("55ac0c06-4721-11ec-81d3-0242ac130025");
        deliveryService.deleteDelivery(uuid);
        assertTrue(deliveryHistoryRepository.findById(uuid).isEmpty());
        assertTrue(orderLineRepository.findById(UUID.fromString("55ac0c06-4721-11ec-81d9-0242ac130015")).isEmpty());
    }
}