package app.security.auth;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import app.api.user.User;
import lombok.Getter;

@Getter
public class Auth extends org.springframework.security.core.userdetails.User implements OAuth2User {
  private static final long serialVersionUID = 1L;
  private User user;
  private Map<String, Object> attributes;

  public Auth(User user) {
    super(user.getEmail(), user.getPassword(),
        user.getRole().stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.name())).collect(Collectors.toSet()));
    this.user = user;
  }

  public Auth(User user, Map<String, Object> attributes) {
    this(user);
    this.attributes = attributes;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return attributes;
  }

  @Override
  public String getName() {
    return String.valueOf(user.getIdx());
  }
}