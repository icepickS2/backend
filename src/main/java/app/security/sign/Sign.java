package app.security.sign;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import app.api.user.User;
import lombok.Getter;

@Getter
public class Sign extends org.springframework.security.core.userdetails.User {
  private static final long serialVersionUID = 1L;
  private User user;

  public Sign(User user) {
    super(user.getEmail(),user.getPassword(),user.getRoles().stream().map(
      r -> new SimpleGrantedAuthority("ROLE_" + r.name())).collect(
        Collectors.toSet()));
  }
}