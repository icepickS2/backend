package app.security.sign;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import app.api.account.Platform;
import app.api.account.Account;
import lombok.Getter;

@Getter
public class Sign extends User implements OAuth2User {
  private static final long serialVersionUID = 1L;
  private Account account;
  private Map<String, Object> attributes;

  public Sign(Account account) {
    //@formatter:off
    super(
      account.getEmail(),
      account.getPlatform()==Platform.ICEPICK?account.getPassword():account.getPlatformToken(),
      account.getRoles()
        .stream()
          .map(
            r -> new SimpleGrantedAuthority("ROLE_" + r.name()))
            .collect(Collectors.toSet())
    );
    //@formatter:on
    this.account = account;
  }

  public Sign(Account account, Map<String, Object> attributes) {
    this(account);
    this.attributes = attributes;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return attributes;
  }

  @Override
  public String getName() {
    return String.valueOf(account.getIdx());
  }
}