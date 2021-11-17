package project.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.persistence.entity.UserEntity;
import project.persistence.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoadingApplicationUserService implements UserDetailsService {

    private static final String ACCOUNT_WITH_EMAIL_NOT_FOUND_MESSAGE = "Account with email %s not found";

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) {
        return new ApplicationUser(findUser(s));
    }

    private UserEntity findUser(String email) {
        Optional<UserEntity> maybeAccount = userRepository.findByEmail(email);
        return maybeAccount.orElseThrow(() -> createUsernameNotFoundException(email));
    }

    private UsernameNotFoundException createUsernameNotFoundException(String email) {
        return new UsernameNotFoundException(String.format(ACCOUNT_WITH_EMAIL_NOT_FOUND_MESSAGE, email));
    }
}
