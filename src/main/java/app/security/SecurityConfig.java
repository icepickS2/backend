package app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import app.api.sign.SignService;

public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  SignService signService;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(signService).passwordEncoder(passwordEncoder);
  }

  @Override
  public void configure(WebSecurity web) {
    // web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    web.ignoring().antMatchers("/**");
  }
}