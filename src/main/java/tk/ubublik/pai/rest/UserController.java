package tk.ubublik.pai.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.ubublik.pai.dto.UserDTO;
import tk.ubublik.pai.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping
    public void createUser(@RequestBody UserDTO user) {
        userService.registerUser(user);
    }
}
