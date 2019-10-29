package app.api.bbs;

import java.util.Map;

public interface BbsService {
  public Map<String, Object> create(Bbs bbs);

  public Map<String, Object> read(Long index);

  public Map<String, Object> list(int pageNum);

  public Map<String, Object> update(Bbs bbs, Long index);

  public Map<String, Object> delete(Long index);
}