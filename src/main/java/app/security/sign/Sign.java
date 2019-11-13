package app.security.sign;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import app.api.user.User;
import lombok.Getter;

@Getter
public class Sign extends org.springframework.security.core.userdetails.User implements OAuth2User {
  private static final long serialVersionUID = 1L;
  private User user;
  private Map<String, Object> attributes;

  public Sign(User user) {
    //@formatter:off
    super(
      user.getEmail(),
      user.getPassword(),
      user.getRoles()
        .stream()
          .map(
            r -> new SimpleGrantedAuthority("ROLE_" + r.name()))
            .collect(Collectors.toSet())
    );
    //@formatter:on
  }

  public Sign(User user, Map<String, Object> attributes) {
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