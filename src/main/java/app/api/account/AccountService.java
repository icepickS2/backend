package app.api.account;

import java.util.Map;

public interface AccountService {
  public Map<String, Object> create(Account user);
  public Map<String, Object> read(Long index);
  public Map<String, Object> list(int pageNum);
  public Map<String, Object> update(Account user, Long index);
  public Map<String, Object> delete(Long index);	
}