package app.security.jwt;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import app.security.sign.Sign;

public class AccessTokenConverter extends JwtAccessTokenConverter {

  public AccessTokenConverter() {
    super();
    this.setSigningKey("key");
  }

  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
    Sign sign = (Sign) authentication.getPrincipal();
    Map<String, Object> info = new HashMap<String, Object>(accessToken.getAdditionalInformation());
    info.put("email", sign.getUser().getEmail());

    DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
    token.setAdditionalInformation(info);
    return super.enhance(token, authentication);
  }
}