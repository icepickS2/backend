package app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableOAuth2Client
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private DefaultOAuth2UserService defaultOAuth2UserService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  //@formatter:off
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .cors()
          .and()
      .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          .and()
      .csrf()
          .disable()
      .formLogin()
          .disable()
      .httpBasic()
          .disable();

    http
      .authorizeRequests()
      .antMatchers("/",
          "/error",
          "/favicon.ico",
          "/**/*.png",
          "/**/*.gif",
          "/**/*.svg",
          "/**/*.jpg",
          "/**/*.html",
          "/**/*.css",
          "/**/*.js")
          .permitAll()
      .antMatchers("/auth/**", "/oauth2/**")
          .permitAll()
      .anyRequest()
          .authenticated();
    
    http
      .oauth2Login()
        .userInfoEndpoint()
          .userService(defaultOAuth2UserService);
  }
  //@formatter:on

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
  }
}