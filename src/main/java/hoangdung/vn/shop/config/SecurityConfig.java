package hoangdung.vn.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //     http
    //         .csrf(Customizer.withDefaults())
    //         .httpBasic(Customizer.withDefaults())
    //         .formLogin(Customizer.withDefaults())
    //         .authorizeHttpRequests(authorize -> authorize
    //             .requestMatchers("/api/v1/users/create").permitAll()
    //             .anyRequest().authenticated()
    //         );

    //     return http.build();
    // }

}
