package project.services.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import project.services.DeliveryHistoryService;
import project.services.dto.DeliveryHistoryDto;
import project.services.dto.GetDeliveryHistoryDto;
import project.services.dto.OrderLineDto;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class DeliveryHistoryServiceImplTest {

    @Autowired
    private DeliveryHistoryService deliveryHistoryService;

    @Test
    void getAllByPeriod() {
        List<DeliveryHistoryDto> allByPeriod = deliveryHistoryService.getAllByPeriod(initGetDeliveryHistoryDto());
        assertEquals(1, allByPeriod.size());
        DeliveryHistoryDto actual = allByPeriod.get(0);
        assertEquals("consumer", actual.getConsumerEmail());
        assertEquals("supplier", actual.getSupplierEmail());
        assertEquals(2, actual.getOrderLines().size());
        OrderLineDto orderLineDto = actual.getOrderLines().get(0);
        assertEquals(BigDecimal.valueOf(100L), orderLineDto.getPrice());
        assertEquals(BigInteger.valueOf(101L), orderLineDto.getWeight());
        assertEquals("Red apple", orderLineDto.getProductName());
        OrderLineDto lineDto = actual.getOrderLines().get(1);
        assertEquals(BigDecimal.valueOf(50L), lineDto.getPrice());
        assertEquals(BigInteger.valueOf(16L), lineDto.getWeight());
        assertEquals("Green apple", lineDto.getProductName());
    }

    private GetDeliveryHistoryDto initGetDeliveryHistoryDto() {
        GetDeliveryHistoryDto getDeliveryHistoryDto = new GetDeliveryHistoryDto();
        getDeliveryHistoryDto.setDateStart(LocalDateTime.of(2020, 10, 30, 0, 0));
        getDeliveryHistoryDto.setDateEnd(LocalDateTime.now().minusHours(1L));
        return getDeliveryHistoryDto;
    }
}