package project.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.errorhandling.exception.UsernameExistException;
import project.persistence.entity.UserEntity;
import project.persistence.repository.UserRepository;
import project.services.UserService;
import project.services.dto.RegistrationUserDto;
import project.services.mapper.UserMapper;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private static final PasswordEncoder PASSWORD_ENCODER = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    private final UserMapper userMapper;

    public void registrationUser(RegistrationUserDto registrationUserDto) {
        if(userRepository.findByEmail(registrationUserDto.getEmail()).isPresent()) {
            throw new UsernameExistException(registrationUserDto.getEmail());
        }
        UserEntity user = userMapper.toEntity(registrationUserDto);
        userRepository.save(encodePassword(user));
    }

    private UserEntity encodePassword(UserEntity userEntity) {
        userEntity.setPassword(PASSWORD_ENCODER.encode(userEntity.getPassword()).substring(8));
        return userEntity;
    }
}
