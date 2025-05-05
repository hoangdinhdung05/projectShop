package hoangdung.vn.shop.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hoangdung.vn.shop.models.User;
import hoangdung.vn.shop.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //create user
    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return this.userService.createUser(user);
    }
    

}
