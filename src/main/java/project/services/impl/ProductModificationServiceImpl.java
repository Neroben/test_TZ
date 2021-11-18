package project.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.errorhandling.exception.UserNotFoundException;
import project.persistence.entity.UserEntity;
import project.persistence.repository.ProductRepository;
import project.persistence.repository.UserRepository;
import project.services.ProductModificationService;
import project.services.dto.ProductInformationDto;
import project.services.mapper.ProductMapper;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductModificationServiceImpl implements ProductModificationService {

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final ProductMapper productMapper;

    public void saveProduct(ProductInformationDto productInformationDto) {
        productRepository.save(productMapper.toEntity(productInformationDto));
    }

    public void deleteProduct(UUID id, String email) {
        UserEntity user = findUserByEmail(email);
        productRepository.deleteByIdAndSupplierId(id, user.getId());
    }

    private UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow((() -> new UserNotFoundException(email)));
    }

}
