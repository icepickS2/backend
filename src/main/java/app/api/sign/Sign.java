package app.api.sign;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import app.api.account.Account;
import app.api.account.AccountRole;

public class Sign extends User {
  private static final long serialVersionUID = 1L;

  public Sign(Account account) {
    super(account.getEmail(), account.getPw(), authorities(account.getRole()));
  }

  private static Collection<? extends GrantedAuthority> authorities(Set<AccountRole> role) {
    return role.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.name())).collect(Collectors.toSet());
  }
}