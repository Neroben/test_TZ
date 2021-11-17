package project.services.dto;

import lombok.Data;
import project.security.GrantedAuthorityEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RegistrationUserDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private GrantedAuthorityEnum authority;

}
