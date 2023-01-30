package livingfantasy.service;

import livingfantasy.entity.User;
import livingfantasy.repository.UserRepository;
import livingfantasy.security.model.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
       Optional<User> user =  userRepository.findUserByUsername(s);
       User u = user.orElseThrow(() -> new UsernameNotFoundException("user not found.."));
       SecurityUser securityUser = new SecurityUser(u);
        return securityUser;
    }
}
