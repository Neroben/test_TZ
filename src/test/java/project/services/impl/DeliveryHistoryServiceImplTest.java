package project.services.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.persistence.entity.OrderLineEntity;
import project.services.DeliveryHistoryService;
import project.services.dto.DeliveryHistoryDto;
import project.services.dto.GetDeliveryHistoryDto;
import project.services.dto.OrderLineDto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeliveryHistoryServiceImplTest extends AbstractIntegrationTest {

    @Autowired
    private DeliveryHistoryService deliveryHistoryService;

    @BeforeEach
    void setUp() {
        OrderLineEntity orderLineEntity = initOrderLineEntity("supplier");
        initDeliveryHistoryEntity(List.of(orderLineEntity));
    }

    @AfterEach
    void dropAll() {
        deleteAll();
    }


    @Test
    void getAllByPeriod() {
        List<DeliveryHistoryDto> allByPeriod = deliveryHistoryService.getAllByPeriod(initGetDeliveryHistoryDto());
        assertEquals(1, allByPeriod.size());
        DeliveryHistoryDto actual = allByPeriod.get(0);
        assertEquals("consumer", actual.getConsumerEmail());
        assertEquals("supplier", actual.getSupplierEmail());
        assertEquals(1, actual.getOrderLines().size());
        OrderLineDto orderLineDto = actual.getOrderLines().get(0);
        assertEquals(BigDecimal.valueOf(50L), orderLineDto.getPrice());
        assertEquals(BigInteger.valueOf(101L), orderLineDto.getWeight());
        assertEquals("product", orderLineDto.getProductName());
    }

    private GetDeliveryHistoryDto initGetDeliveryHistoryDto() {
        GetDeliveryHistoryDto getDeliveryHistoryDto = new GetDeliveryHistoryDto();
        getDeliveryHistoryDto.setDateStart(LocalDateTime.of(2020, 10, 30, 0, 0));
        getDeliveryHistoryDto.setDateEnd(LocalDateTime.now().plusHours(1L));
        return getDeliveryHistoryDto;
    }
}