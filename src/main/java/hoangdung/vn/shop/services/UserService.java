package hoangdung.vn.shop.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hoangdung.vn.shop.models.User;
import hoangdung.vn.shop.models.requests.auth.LoginRequest;
import hoangdung.vn.shop.models.requests.users.ReqCreateUser;
import hoangdung.vn.shop.models.responses.auth.LoginResponse;
import hoangdung.vn.shop.models.responses.users.ResCreateUser;
import hoangdung.vn.shop.models.responses.users.UserResponse;
import hoangdung.vn.shop.repositories.UserRepository;
import hoangdung.vn.shop.util.errors.IdInvalidException;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //login user
    public Object authenticate(LoginRequest loginRequest) throws IdInvalidException {
        try {

            User user = this.userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(
                () ->  new IdInvalidException("Email hoặc mật khẩu không chính xác")); 
            
            //check password 
            if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassWord())) {
                throw new IdInvalidException("Email hoặc mật khẩu không chính xác");
            }

            UserResponse userResponse = new UserResponse(user.getId(), user.getEmail(), user.getUserName());
            //generate token khi thành công
            String token = jwtService.generateToken(user.getId(), user.getEmail());
            return new LoginResponse(token, userResponse);
        } catch (Exception e) {
            throw new IdInvalidException("Lỗi xác thực người dùng: " + e.getMessage());
        }
    }

    //create user
    public ResCreateUser createUser(ReqCreateUser reqCreateUser) {
        String passWord = this.passwordEncoder.encode(reqCreateUser.getPassword());
    
        User newUser = new User();
        newUser.setUserName(reqCreateUser.getUsername());
        newUser.setPassWord(passWord);
        newUser.setEmail(reqCreateUser.getEmail());
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());

        // Lưu user vào database
        userRepository.save(newUser);

        return new ResCreateUser();
    }
    

}
