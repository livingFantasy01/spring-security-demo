package livingfantasy.security.provider;

import livingfantasy.authentication.UsernamePasswordAuthentication;
import livingfantasy.service.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsernamePasswordAuthProvider implements AuthenticationProvider {

    @Autowired
    private JpaUserDetailsService jpaUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserDetails u = jpaUserDetailsService.loadUserByUsername(username);
        if(passwordEncoder.matches(password,u.getPassword())){
            return new UsernamePasswordAuthentication(username,password, u.getAuthorities());
        }

        throw new BadCredentialsException("Bad Credentials !!");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthentication.class.equals(aClass);
    }
}
