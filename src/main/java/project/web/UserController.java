package project.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.services.UserService;
import project.services.dto.RegistrationUserDto;

import javax.validation.Valid;

import static project.security.configuration.ResourceServerConfiguration.BASE_API;
import static project.web.UserController.USER_URL;

@RestController
@RequestMapping(BASE_API + USER_URL)
@RequiredArgsConstructor
public class UserController {

    public static final String USER_URL = "/user";

    private final UserService userService;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public void registrationUser(@RequestBody @Valid RegistrationUserDto registrationUserDto) {
        userService.registrationUser(registrationUserDto);
    }

}
