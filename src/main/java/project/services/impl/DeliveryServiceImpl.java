package project.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.persistence.entity.DeliveryHistoryEntity;
import project.persistence.entity.OrderLineEntity;
import project.persistence.entity.ProductEntity;
import project.persistence.repository.*;
import project.services.DeliveryService;
import project.services.dto.AcceptDeliveryDto;
import project.services.dto.CreateOrderLineDto;
import project.services.mapper.DeliveryHistoryMapper;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryHistoryRepository deliveryRepository;

    private final OrderLineRepository orderLineRepository;

    private final ProductRepository productRepository;

    private final ProductPriceRepository productPriceRepository;

    private final UserRepository userRepository;

    private final DeliveryHistoryMapper deliveryHistoryMapper;

    @Transactional
    public void acceptDelivery(AcceptDeliveryDto acceptDeliveryDto, String email) {
        DeliveryHistoryEntity delivery = deliveryHistoryMapper.toEntity(acceptDeliveryDto, getConsumerId(email));
        createAllOrderLine(deliveryRepository.save(delivery), acceptDeliveryDto);
    }

    private UUID getConsumerId(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User is not found"))
                .getId();
    }

    private void createAllOrderLine(DeliveryHistoryEntity delivery, AcceptDeliveryDto acceptDeliveryDto) {
        UUID supplierId = getSupplierIdByOrderLine(acceptDeliveryDto.getOrderLines().get(0));
        acceptDeliveryDto.getOrderLines()
                .forEach(orderLine -> createOrderLine(orderLine, delivery, supplierId));
    }

    private UUID getSupplierIdByOrderLine(CreateOrderLineDto orderLineDto) {
        ProductEntity product = productRepository.findById(orderLineDto.getProductId()).orElseThrow(() -> new EntityNotFoundException(""));
        return product.getSupplier().getId();
    }

    private void createOrderLine(CreateOrderLineDto orderLineDto, DeliveryHistoryEntity delivery, UUID supplierId) {
        ProductEntity product = productRepository.findById(orderLineDto.getProductId()).orElseThrow(() -> new EntityNotFoundException(""));
        if (product.getSupplier().getId() != supplierId) {
            throw new RuntimeException("Oouups");
        }
        orderLineRepository.save(
                OrderLineEntity.builder()
                        .price(getActualPrice(product))
                        .weight(orderLineDto.getWeight())
                        .delivery(delivery)
                        .product(product)
                        .build());
    }

    private BigDecimal getActualPrice(ProductEntity product) {
        return productPriceRepository.findActualPrice(product, LocalDateTime.now())
                .orElseThrow(() -> new RuntimeException("Ouups"))
                .getPrice();
    }

    public void deleteDelivery(UUID id) {
        DeliveryHistoryEntity delivery = deliveryRepository.findById(id).orElseThrow(() -> new RuntimeException("Delivery not found"));
        deliveryRepository.delete(delivery);
    }
}
