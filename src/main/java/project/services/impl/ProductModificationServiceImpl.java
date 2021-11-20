package project.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.errorhandling.exception.ProductNotFoundException;
import project.errorhandling.exception.UserNotFoundException;
import project.persistence.entity.UserEntity;
import project.persistence.repository.OrderLineRepository;
import project.persistence.repository.ProductRepository;
import project.persistence.repository.UserRepository;
import project.services.ProductModificationService;
import project.services.dto.ProductInformationDto;
import project.services.mapper.ProductMapper;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductModificationServiceImpl implements ProductModificationService {

    private final ProductRepository productRepository;

    private final OrderLineRepository orderLineRepository;

    private final UserRepository userRepository;

    private final ProductMapper productMapper;

    public void saveProduct(ProductInformationDto productInformationDto, String email) {
        UserEntity userByEmail = findUserByEmail(email);
        productRepository.save(productMapper.toEntity(productInformationDto, userByEmail.getId()));
    }

    @Transactional
    public void deleteProduct(UUID id, String email) {
        UserEntity user = findUserByEmail(email);
        productRepository.findByIdAndSupplierId(id, user.getId())
                .orElseThrow(() -> new ProductNotFoundException(id.toString()))
                .getOrderLines().forEach(a -> {
            a.setProduct(null);
            orderLineRepository.save(a);
        });
        productRepository.deleteByIdAndSupplierId(id, user.getId());
    }

    private UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow((() -> new UserNotFoundException(email)));
    }

}
