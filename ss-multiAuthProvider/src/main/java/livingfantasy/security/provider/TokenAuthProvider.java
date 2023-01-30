package livingfantasy.security.provider;

import livingfantasy.authentication.TokenAuthentication;
import livingfantasy.manager.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthProvider implements AuthenticationProvider {

    @Autowired
    private TokenManager tokenManager;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

       String token = String.valueOf(authentication.getPrincipal());
       if(token.contains(token)){
           return new TokenAuthentication(token,null);
       }

        throw new BadCredentialsException("No token !!!");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TokenAuthentication.class.equals(aClass);
    }
}
