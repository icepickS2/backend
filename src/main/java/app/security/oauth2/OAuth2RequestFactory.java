package app.security.oauth2;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

@Component
public class OAuth2RequestFactory extends DefaultOAuth2RequestFactory {
  @Autowired
  private TokenStore tokenStore;
  @Autowired
  private UserDetailsService userDetailsService;

  public OAuth2RequestFactory(ClientDetailsService clientDetailsService) {
    super(clientDetailsService);
    // this.setCheckUserScopes(true);
  }

  //@formatter:off
  @Override
  public TokenRequest createTokenRequest(Map<String, String> requestParameters, ClientDetails authenticatedClient) {
    
    if (requestParameters.get("grant_type").equals("refresh_token")) {
      OAuth2RefreshToken refreshToken = tokenStore.readRefreshToken(requestParameters.get("refresh_token"));
      OAuth2Authentication authentication = tokenStore.readAuthenticationForRefreshToken(refreshToken);

      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
        authentication.getName(),
        null,
        userDetailsService.loadUserByUsername(authentication.getName()).getAuthorities()
      );

      SecurityContextHolder.getContext().setAuthentication(authenticationToken);

      
    }

    return super.createTokenRequest(requestParameters, authenticatedClient);
  }
  //@formatter:on
}