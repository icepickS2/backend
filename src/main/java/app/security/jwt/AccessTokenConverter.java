package app.security.jwt;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.stereotype.Component;

import app.security.auth.Auth;

@Component
public class AccessTokenConverter extends JwtAccessTokenConverter {

  public AccessTokenConverter() {
    super();
    //@formatter:off
    this.setKeyPair(
        new KeyStoreKeyFactory(
          new ClassPathResource("jwt.jks"), "password".toCharArray()).getKeyPair("jwt"));
    //@formatter:on
  }

  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
    Auth auth = (Auth) authentication.getPrincipal();
    Map<String, Object> info = new HashMap<String, Object>(accessToken.getAdditionalInformation());
    info.put("email", auth.getUser().getEmail());

    DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
    token.setAdditionalInformation(info);
    return super.enhance(token, authentication);
  }
}
