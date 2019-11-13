package app.api.account;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.config.Message;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

  @Autowired
  AccountRepository accountRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  Message map;

  @Override
  public Map<String, Object> create(Account account) {
    account.setPassword(passwordEncoder.encode(account.getPassword()));
    map.put("account", accountRepository.save(account));
    return map;
  }

  @Override
  public Map<String, Object> read(Long index) {
    return null;
  }

  @Override
  public Map<String, Object> list(int pageNum) {
    return null;
  }

  @Override
  public Map<String, Object> update(Account user, Long index) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return null;
  }

  @Override
  public Map<String, Object> delete(Long index) {
    return null;
  }

}