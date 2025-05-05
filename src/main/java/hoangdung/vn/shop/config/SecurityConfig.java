package hoangdung.vn.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //check authorization, authentication
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http
                .authorizeHttpRequests(
                        authz -> authz
                                .requestMatchers("/").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(f -> f.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
            // .csrf(csrf -> csrf.disable())
         return http.build();
     }

}
