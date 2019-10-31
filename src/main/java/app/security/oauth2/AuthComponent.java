package app.security.oauth2;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpointAuthenticationFilter;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import app.api.user.User;

@Configuration
public class AuthComponent {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private ClientDetailsService clientDetailsService;

  @Bean
  public OAuth2RequestFactory requestFactory() {
    CustomOauth2RequestFactory requestFactory = new CustomOauth2RequestFactory(clientDetailsService);
    requestFactory.setCheckUserScopes(true);
    return requestFactory;
  }

  @Bean
  public TokenEndpointAuthenticationFilter tokenEndpointAuthenticationFilter() {
    return new TokenEndpointAuthenticationFilter(authenticationManager, requestFactory());
  }

  @Bean
  public TokenStore tokenStore() {
    return new JwtTokenStore(jwtAccessTokenConverter());
  }

  @Bean
  public JwtAccessTokenConverter jwtAccessTokenConverter() {
    JwtAccessTokenConverter converter = new CustomTokenEnhancer();
    converter.setKeyPair(
        new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "password".toCharArray()).getKeyPair("jwt"));
    return converter;
  }

  class CustomTokenEnhancer extends JwtAccessTokenConverter {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
      User user = (User) authentication.getPrincipal();
      Map<String, Object> info = new HashMap<String, Object>(accessToken.getAdditionalInformation());
      info.put("email", user.getEmail());

      DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
      customAccessToken.setAdditionalInformation(info);

      return super.enhance(customAccessToken, authentication);
    }
  }

  class CustomOauth2RequestFactory extends DefaultOAuth2RequestFactory {
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private UserDetailsService userDetailsService;

    public CustomOauth2RequestFactory(ClientDetailsService clientDetailsService) {
      super(clientDetailsService);
    }

    @Override
    public TokenRequest createTokenRequest(Map<String, String> requestParameters, ClientDetails authenticatedClient) {
      if (requestParameters.get("grant_type").equals("refresh_token")) {
        OAuth2Authentication authentication = tokenStore
            .readAuthenticationForRefreshToken(tokenStore.readRefreshToken(requestParameters.get("refresh_token")));
        SecurityContextHolder.getContext()
            .setAuthentication(new UsernamePasswordAuthenticationToken(authentication.getName(), null,
                userDetailsService.loadUserByUsername(authentication.getName()).getAuthorities()));
      }
      return super.createTokenRequest(requestParameters, authenticatedClient);
    }
  }
}