package livingfantasy.security;

import livingfantasy.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@Component
public class CustomAuthenticationFilter implements Filter {

    @Autowired
    private AuthenticationManager manager;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest)  request;
       String auth =  httpServletRequest.getHeader("Authorization");

       CustomAuthentication customAuthentication = new CustomAuthentication(auth,null);
       Authentication authenticate = manager.authenticate(customAuthentication);

       if(authenticate.isAuthenticated()){
           SecurityContextHolder.getContext().setAuthentication(authenticate);
           chain.doFilter(request,response);
       }

    }
}
