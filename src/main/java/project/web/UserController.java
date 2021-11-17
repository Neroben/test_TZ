package project.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.services.UserService;
import project.services.dto.RegistrationUserDto;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public void registrationUser(@RequestBody @Valid RegistrationUserDto registrationUserDto) {
        userService.registrationUser(registrationUserDto);
    }

}
