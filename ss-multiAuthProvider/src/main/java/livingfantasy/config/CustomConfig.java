package livingfantasy.config;

import livingfantasy.security.filter.TokenAuthFilter;
import livingfantasy.security.filter.UsernamePasswordAuthFilter;
import livingfantasy.security.provider.OtpAuthProvider;
import livingfantasy.security.provider.TokenAuthProvider;
import livingfantasy.security.provider.UsernamePasswordAuthProvider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class CustomConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsernamePasswordAuthProvider usernamePasswordAuthProvider;

    @Autowired
    private OtpAuthProvider otpAuthProvider;


    @Autowired
    private TokenAuthProvider tokenAuthProvider;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(usernamePasswordAuthProvider)
                .authenticationProvider(otpAuthProvider)
                .authenticationProvider(tokenAuthProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(getTokenAuthFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(getUsernamePasswordAuthFilter(), BasicAuthenticationFilter.class);
    }

    @Bean
    public TokenAuthFilter getTokenAuthFilter() {
        return new TokenAuthFilter();
    }

    @Bean
    public UsernamePasswordAuthFilter getUsernamePasswordAuthFilter(){
        return new UsernamePasswordAuthFilter();
    }

    @Bean
    public InitializingBean initializingBean(){
        return () -> {
            SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
        };
    }
}
