package project.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.persistence.repository.UserRepository;
import project.security.GrantedAuthorityEnum;
import project.services.SupplierService;
import project.services.dto.SupplierDto;
import project.services.mapper.UserMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public List<SupplierDto> getAllSupplier() {
        return userMapper.toSuppliers(userRepository.findAllByAuthority(GrantedAuthorityEnum.SUPPLIER));
    }
}
