package project.persistence.entity;

import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;
import project.security.GrantedAuthorityEnum;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class UserEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "user_uuid", strategy = "uuid")
    private UUID id;

    private String email;

    private String password;

    private GrantedAuthorityEnum authority;

}
