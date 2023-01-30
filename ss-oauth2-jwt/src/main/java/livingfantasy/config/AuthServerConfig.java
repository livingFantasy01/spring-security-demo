package livingfantasy.config;

import livingfantasy.security.JPAClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(jpaClientDetailsService());
    }

    @Bean
    public JPAClientDetailsService jpaClientDetailsService(){
        return new JPAClientDetailsService();
    }

    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(converter());
    }

/*    @Bean
    public JwtAccessTokenConverter converter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("secret");
        return jwtAccessTokenConverter;
    }*/

    @Bean
    public JwtAccessTokenConverter converter() {
        var conv = new JwtAccessTokenConverter();

        KeyStoreKeyFactory keyFactory =
                new KeyStoreKeyFactory(
                        new ClassPathResource("key123.jks"),
                        "key123".toCharArray()
                );

        conv.setKeyPair(keyFactory.getKeyPair("key123"));

        return conv;
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
        endpoints.tokenStore(tokenStore());
        endpoints.accessTokenConverter(converter());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()"); //enable oauth/token_key
    }
}
