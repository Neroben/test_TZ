package project.services.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegistrationUserDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
