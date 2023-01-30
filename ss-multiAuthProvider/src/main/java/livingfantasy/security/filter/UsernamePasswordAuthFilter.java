package livingfantasy.security.filter;

import livingfantasy.authentication.OtpAuthentication;
import livingfantasy.authentication.UsernamePasswordAuthentication;
import livingfantasy.entity.Otp;
import livingfantasy.manager.TokenManager;
import livingfantasy.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;


public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private OtpRepository otpRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String username = request.getHeader("username");
        String password = request.getHeader("password");
        String otp = request.getHeader("otp");

        if (otp == null) {
            UsernamePasswordAuthentication usernamePasswordAuthentication = new UsernamePasswordAuthentication(username, password);
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthentication);

            String code = String.valueOf(new Random().nextInt(9999) + 1000);
            Otp otpEntity = new Otp();
            otpEntity.setUsername(username);
            otpEntity.setOtp(code);
            otpRepository.save(otpEntity);
        } else {
            OtpAuthentication otpAuthentication = new OtpAuthentication(username, password);
            Authentication authentication = authenticationManager.authenticate(otpAuthentication);
            String code = String.valueOf(UUID.randomUUID());
            response.setHeader("Authorization", code);
            tokenManager.add(code);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }
}
