package livingfantasy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.security.KeyPair;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
/*
    @Value("${publicKey}")
    private String publicKey;

    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(converter());
    }

    public JwtAccessTokenConverter converter(){
        var conv = new JwtAccessTokenConverter();
        String verifierKey = String.format("-----BEGIN PUBLIC KEY-----%s-----END PUBLIC KEY-----", publicKey);
        conv.setVerifierKey(verifierKey);

        return conv;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore());
    }*/


}
