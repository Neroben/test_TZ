package project.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.persistence.entity.UserEntity;
import project.security.GrantedAuthorityEnum;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findAllByAuthority(GrantedAuthorityEnum grantedAuthorityEnum);

}
