package hoangdung.vn.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hoangdung.vn.shop.models.requests.auth.LoginRequest;
import hoangdung.vn.shop.services.UserService;
import hoangdung.vn.shop.util.errors.IdInvalidException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) throws IdInvalidException {

        Object loginResponse = userService.authenticate(loginRequest);
        if (loginResponse != null) {
            return ResponseEntity.ok().body(loginResponse);
        } else {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
    }

}
