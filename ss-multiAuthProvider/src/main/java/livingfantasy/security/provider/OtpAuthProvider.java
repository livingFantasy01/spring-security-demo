package livingfantasy.security.provider;

import livingfantasy.authentication.OtpAuthentication;
import livingfantasy.authentication.UsernamePasswordAuthentication;
import livingfantasy.entity.Otp;
import livingfantasy.entity.User;
import livingfantasy.repository.OtpRepository;
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
import java.util.Optional;

@Component
public class OtpAuthProvider implements AuthenticationProvider {

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String otp = (String) authentication.getCredentials();

        Optional<Otp> o = otpRepository.findOtpByUsername(username);
       if(o.isPresent()){
        return new OtpAuthentication(username,otp, List.of(() -> "read"));
       }

        throw new BadCredentialsException("Bad Otp !!");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return OtpAuthentication.class.equals(aClass);
    }
}
