package app.security.sign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import app.api.account.Account;
import app.api.account.AccountRepository;

@Service
public class SignServiceImpl implements SignService {

  @Autowired
  AccountRepository accountRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Account account = accountRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(""));
    return new Sign(account);
  }
}