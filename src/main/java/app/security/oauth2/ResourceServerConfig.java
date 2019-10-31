package app.security.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  // @Override
  // public void configure(ResourceServerSecurityConfigurer resources) throws
  // Exception {
  // // resources.resourceId("event");
  // }

  // @Override
  // public void configure(HttpSecurity http) throws Exception {
  // // http.anonymous().and().authorizeRequests().mvcMatchers(HttpMethod.GET,
  // // "/api/**").permitAll().anyRequest()
  // // .authenticated();
  // // http.exceptionHandling().accessDeniedHandler(new
  // // OAuth2AccessDeniedHandler());
  // }
}