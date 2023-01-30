package livingfantasy.config;

import livingfantasy.entity.SecurityUser;
import livingfantasy.entity.User;
import livingfantasy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class JPAUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Optional<User> user =   userRepository.findUserByUsername(username);
      user.orElseThrow(() -> new UsernameNotFoundException("User not found"));

      return new SecurityUser(user.get());
    }
}
