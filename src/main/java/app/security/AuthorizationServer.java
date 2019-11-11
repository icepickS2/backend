package app.security;

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
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpointAuthenticationFilter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import app.security.oauth2.RequestFactory;

@Configuration
@EnableAuthorizationServer
@SuppressWarnings("all")
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

  @Autowired
  private DataSource dataSource;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsService userService;

  @Autowired
  private ClientDetailsService clientDetailsService;

  @Autowired
  private TokenStore tokenStore;

  @Autowired
  private ApprovalStore approvalStore;

  @Autowired
  private JwtAccessTokenConverter jwtAccessTokenConverter;

  @Autowired
  RequestFactory requestFactory;

  @Bean
  public OAuth2RequestFactory getRequestFactory() {
    RequestFactory requestFactory = new RequestFactory(clientDetailsService);
    requestFactory.setCheckUserScopes(true);
    return requestFactory;
  }

  @Bean
  public TokenEndpointAuthenticationFilter tokenEndpointAuthenticationFilter() {
    return new TokenEndpointAuthenticationFilter(authenticationManager, getRequestFactory());
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    // JdbcClientDetailsService
    // ClientDetails
    // clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
    //@formatter:off
    // clients.inMemory()
    // .withClient("icepick")
    // .secret("icepick")
    // .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
    // .scopes("read", "write")
    // .authorities("client")
    // .accessTokenValiditySeconds(3600) // 1 hour
    // .refreshTokenValiditySeconds(2592000)
    // ; // 30 days
    // System.out.println(clients);
    //@formatter:on
    clients.withClientDetails(clientDetailsService);
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    //@formatter:off
    endpoints.tokenStore(tokenStore).tokenEnhancer(jwtAccessTokenConverter);
    endpoints.authenticationManager(authenticationManager).userDetailsService(userService);
    endpoints.approvalStore(approvalStore);
    endpoints.requestFactory(requestFactory);
    endpoints.setClientDetailsService(clientDetailsService);
    //@formatter:on
    System.out.println(endpoints);
    // TokenEndpointAuthenticationFilter
    // AuthorizationEndpoint
    // CheckTokenEndpoint
    // TokenEndpoint

  }

}