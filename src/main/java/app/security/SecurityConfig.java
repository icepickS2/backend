package app.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  UserDetailsService userService;

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // http.csrf().disable();
    // http.anonymous().disable();
    // http.authorizeRequests().antMatchers("/").permitAll();
    // TokenEndpoint
    http.csrf().disable().exceptionHandling()
        .authenticationEntryPoint(
            (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
        .and().authorizeRequests().antMatchers("/**").authenticated().and().httpBasic();
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers("/**");
  }
}