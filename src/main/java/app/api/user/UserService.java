package app.api.user;

import java.util.Map;

public interface UserService {
  public Map<String, Object> create(User user);
  public Map<String, Object> read(Long index);
  public Map<String, Object> list(int pageNum);
  public Map<String, Object> update(User user, Long index);
  public Map<String, Object> delete(Long index);	
}