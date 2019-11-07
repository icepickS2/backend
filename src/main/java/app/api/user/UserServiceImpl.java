package app.api.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.util.Msg;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  Msg map;

  @Override
  public Map<String, Object> create(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    User user2 = userRepository.save(user);
    map.put("user", user2);
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
  public Map<String, Object> update(User user, Long index) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return null;
  }

  @Override
  public Map<String, Object> delete(Long index) {
    return null;
  }

}