package livingfantasy.security;

import livingfantasy.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {


    @Value("${key}")
    private String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String name = (String) authentication.getPrincipal();
        if (key.equals(name)) {
            return new CustomAuthentication(null, null, null);
        } else {
            throw new BadCredentialsException("Error!!!");
        }
    }

    @Override
    public boolean supports(Class<?> authType) {
        return CustomAuthentication.class.equals(authType);
    }
}
