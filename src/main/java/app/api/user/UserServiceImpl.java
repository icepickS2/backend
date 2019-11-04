package app.api.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceImpl implements UserService {

  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public Map<String, Object> create(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return null;
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
  public Map<String, Object> update(User user, Long index) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return null;
  }

  @Override
  public Map<String, Object> delete(Long index) {
    return null;
  }

  
}