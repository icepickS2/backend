
package app.util;

import java.util.Optional;

import app.api.account.Account;

public interface JwtTokenParser {
  Optional<Account> getOwner(String token);
}