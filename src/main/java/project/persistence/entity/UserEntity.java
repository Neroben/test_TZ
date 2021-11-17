package project.persistence.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import project.security.GrantedAuthorityEnum;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "user_uuid", strategy = "uuid")
    private UUID id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private GrantedAuthorityEnum authority;

}
