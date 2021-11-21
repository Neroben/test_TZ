package project.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.errorhandling.exception.ActualPriceNotFoundException;
import project.errorhandling.exception.OrderLinePermissionException;
import project.errorhandling.exception.ProductNotFoundException;
import project.errorhandling.exception.UserNotFoundException;
import project.persistence.entity.DeliveryHistoryEntity;
import project.persistence.entity.OrderLineEntity;
import project.persistence.entity.ProductEntity;
import project.persistence.entity.UserEntity;
import project.persistence.repository.DeliveryHistoryRepository;
import project.persistence.repository.OrderLineRepository;
import project.persistence.repository.ProductRepository;
import project.persistence.repository.UserRepository;
import project.services.DeliveryService;
import project.services.ProductService;
import project.services.dto.AcceptDeliveryDto;
import project.services.dto.CreateOrderLineDto;
import project.services.mapper.DeliveryHistoryMapper;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryHistoryRepository deliveryRepository;

    private final OrderLineRepository orderLineRepository;

    private final ProductRepository productRepository;

    private final ProductService productService;

    private final UserRepository userRepository;

    private final DeliveryHistoryMapper deliveryHistoryMapper;

    @Transactional
    public void acceptDelivery(AcceptDeliveryDto acceptDeliveryDto, String email) {
        DeliveryHistoryEntity delivery = deliveryHistoryMapper.toEntity(acceptDeliveryDto, findUser(email).getId());
        createAllOrderLine(deliveryRepository.save(delivery), acceptDeliveryDto);
    }

    private UserEntity findUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    private void createAllOrderLine(DeliveryHistoryEntity delivery, AcceptDeliveryDto acceptDeliveryDto) {
        UUID supplierId = getSupplierIdByOrderLine(acceptDeliveryDto.getOrderLines().get(0));
        acceptDeliveryDto.getOrderLines()
                .forEach(orderLine -> createOrderLine(orderLine, delivery, supplierId));
    }

    private UUID getSupplierIdByOrderLine(CreateOrderLineDto orderLineDto) {
        ProductEntity product = productRepository.findById(orderLineDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(orderLineDto.getProductId().toString()));
        return product.getSupplier().getId();
    }

    private void createOrderLine(CreateOrderLineDto orderLineDto, DeliveryHistoryEntity delivery, UUID supplierId) {
        ProductEntity product = productRepository.findById(orderLineDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(orderLineDto.getProductId().toString()));
        if (product.getSupplier().getId() != supplierId) {
            throw new OrderLinePermissionException();
        }
        orderLineRepository.save(
                OrderLineEntity.builder()
                        .price(productService.getActualPrice(product)
                                .orElseThrow(() -> new ActualPriceNotFoundException(product.getId().toString())))
                        .weight(orderLineDto.getWeight())
                        .delivery(delivery)
                        .product(product)
                        .build());
    }

    public void deleteDelivery(UUID id) {
        deliveryRepository.deleteById(id);
    }
}
