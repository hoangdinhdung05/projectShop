package hoangdung.vn.shop.services;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hoangdung.vn.shop.models.User;
import hoangdung.vn.shop.models.requests.users.ReqCreateUser;
import hoangdung.vn.shop.models.responses.users.ResCreateUser;
import hoangdung.vn.shop.repositories.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

        return ResCreateUser.builder()
                .id(newUser.getId())
                .username(newUser.getUserName())
                .email(newUser.getEmail())
                .createdAt(newUser.getCreatedAt())
                .updatedAt(newUser.getUpdatedAt())
                .build();
    }
    

}
