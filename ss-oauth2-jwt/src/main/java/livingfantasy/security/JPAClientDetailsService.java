package livingfantasy.security;

import livingfantasy.entity.Client;
import livingfantasy.entity.SecurityClient;
import livingfantasy.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

import java.util.Optional;
@Configuration
public class JPAClientDetailsService implements ClientDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {

        return clientRepository.findByClientId(s)
                .map(c -> new SecurityClient(c))
                .orElseThrow(() -> new ClientRegistrationException("Error !!"));


    }
}
