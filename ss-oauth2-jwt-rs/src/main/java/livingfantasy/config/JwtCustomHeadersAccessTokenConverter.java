/*
package livingfantasy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.JsonParser;
import org.springframework.security.oauth2.common.util.JsonParserFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.Collections;
import java.util.Map;

public class JwtCustomHeadersAccessTokenConverter extends JwtAccessTokenConverter {

    final RsaSigner signer;
    private JsonParser objectMapper = JsonParserFactory.create();


    public JwtCustomHeadersAccessTokenConverter(KeyPair keyPair) {
        super();
        super.setKeyPair(keyPair);
        this.signer = new RsaSigner((RSAPrivateKey) keyPair.getPrivate());
    }

    @Override
    protected String encode(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        String content;
        try {
            content = this.objectMapper.formatMap(getAccessTokenConverter().convertAccessToken(accessToken, authentication));
        } catch (Exception ex) {
            throw new IllegalStateException("Cannot convert access token to JSON", ex);
        }
        Map<String, String> customHeaders = Collections.singletonMap("kid", "my_kid");
        String token = JwtHelper.encode(content, this.signer*/
/*, this.customHeaders*//*
)
                .getEncoded();
        return token;
    }


}*/
