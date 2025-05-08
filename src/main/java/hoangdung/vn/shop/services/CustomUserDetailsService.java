package hoangdung.vn.shop.services;

import java.util.Collections;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hoangdung.vn.shop.models.User;
import hoangdung.vn.shop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
 
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = this.userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new UsernameNotFoundException("User không tồn tại"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassWord(),
                Collections.emptyList()
        );
    }
    
}
