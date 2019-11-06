package app.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import app.security.oauth2.RequestFactory;

@Configuration
@EnableAuthorizationServer
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
  private TokenStore tokenStore;

  @Autowired
  private JwtAccessTokenConverter jwtAccessTokenConverter;

  @Autowired
  RequestFactory requestFactory;

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    // JdbcClientDetailsService
    // ClientDetails
    // clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
    clients.inMemory().withClient("clientapp").secret("123456").scopes("read_profile").authorizedGrantTypes("password",
        "authorization_code", "refresh_token");
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
    endpoints.requestFactory(requestFactory);
    //@formatter:on

    // AuthorizationEndpoint
    // CheckTokenEndpoint
    // TokenEndpoint

  }

}