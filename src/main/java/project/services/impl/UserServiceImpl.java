package project.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.errorhandling.exception.UsernameExistException;
import project.persistence.repository.UserRepository;
import project.services.UserService;
import project.services.dto.RegistrationUserDto;
import project.services.mapper.UserMapper;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public void registrationUser(RegistrationUserDto registrationUserDto) {
        if(userRepository.findByEmail(registrationUserDto.getEmail()).isPresent()) {
            throw new UsernameExistException(registrationUserDto.getEmail());
        }
        userRepository.save(userMapper.toEntity(registrationUserDto));
    }
}
