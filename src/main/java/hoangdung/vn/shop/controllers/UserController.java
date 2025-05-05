package hoangdung.vn.shop.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import hoangdung.vn.shop.models.requests.users.ReqCreateUser;
import hoangdung.vn.shop.models.responses.users.ResCreateUser;
import hoangdung.vn.shop.services.UserService;
import hoangdung.vn.shop.util.anotations.ApiMessage;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //create user
    @PostMapping("/create")
    @ApiMessage("Create user") // Nếu dùng Swagger 3 (OpenAPI), dùng annotation này
    public ResponseEntity<ResCreateUser> createUser(@RequestBody @Valid ReqCreateUser reqCreateUser) {
    
        // Gọi service để xử lý logic tạo user và trả về DTO chuẩn
        ResCreateUser resCreateUser = userService.createUser(reqCreateUser);
    
        // Trả về HTTP 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(resCreateUser);
    }
    
    

}
