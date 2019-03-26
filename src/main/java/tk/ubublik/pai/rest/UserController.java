package tk.ubublik.pai.rest;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.ubublik.pai.dto.UserDTO;

@RestController
@RequestMapping("/users")
public class UserController {

    @PutMapping
    public void createUser(@RequestBody UserDTO user) {
    }

    @RequestMapping("/logout")
    public void logout() {

    }
}
