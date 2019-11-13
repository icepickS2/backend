package app.util;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.api.account.Account;
import app.api.account.AccountRepository;

@Component
// username 을 매개변수로 받고 db에 있다면 해당 account반환하는 짭 parser
public class TempJwtTokenParser implements JwtTokenParser {
  @Autowired
  private AccountRepository accountRepository;

  @Override
  public Optional<Account> getOwner(String token) {
    return accountRepository.findByEmail(token);
  }
}