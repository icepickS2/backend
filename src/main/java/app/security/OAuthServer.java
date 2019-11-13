package app.security;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import app.security.jwt.AccessTokenConverter;
import app.security.oauth2.RequestFactory;

@Configuration
@EnableAuthorizationServer
public class OAuthServer extends AuthorizationServerConfigurerAdapter {

  @Autowired
  private DataSource dataSource;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private TokenStore tokenStore;

  @Autowired
  private JwtAccessTokenConverter accessTokenConverter;

  @Autowired
  RequestFactory requestFactory;

  @Bean
  public JwtAccessTokenConverter jwtAccessTokenConverter() {
    JwtAccessTokenConverter converter = new AccessTokenConverter();
    return converter;
  }

  @Bean
  public TokenStore tokenStore() {
    return new JwtTokenStore(accessTokenConverter);
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    TokenEnhancerChain tokenEnhancer = new TokenEnhancerChain();
    List<TokenEnhancer> list = Arrays.asList(accessTokenConverter);
    tokenEnhancer.setTokenEnhancers(list);

    endpoints.tokenStore(tokenStore).accessTokenConverter(accessTokenConverter).tokenEnhancer(tokenEnhancer);
    endpoints.authenticationManager(authenticationManager);
    endpoints.userDetailsService(userDetailsService);

    // TokenEndpoint
  }
}