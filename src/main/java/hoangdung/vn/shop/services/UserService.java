package hoangdung.vn.shop.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hoangdung.vn.shop.models.User;
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
    public User createUser(User user) {
        String passWord = this.passwordEncoder.encode(user.getPassWord());
    
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setPassWord(passWord);
        newUser.setEmail(user.getEmail());
        newUser.setPhone(user.getPhone());
        newUser.setAge(user.getAge());
        newUser.setAddress(user.getAddress());
        newUser.setGender(user.getGender());
    
        return userRepository.save(newUser);
    }
    

}
