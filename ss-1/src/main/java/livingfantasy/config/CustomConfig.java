package livingfantasy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CustomConfig {

    @Bean
    public UserDetailsService userDetailsService() {

       /* InMemoryUserDetailsManager uds = new InMemoryUserDetailsManager();
        UserDetails user1 = User.builder()
                .username("test")
                .password("test")
                .authorities(
                        "read"
                ).build();

        uds.createUser(user1);
        return uds;*/

        return new JPAUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
