package app.api.bbs;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.module.Pagination;
import app.util.Msg;

@Service
public class BbsServiceImpl implements BbsService {

  @Autowired
  BbsRepository bbsRepository;

  @Autowired
  Pagination pagination;
  @Autowired
  Msg map;

  @Override
  public Map<String, Object> create(Bbs bbs) {
    map.put("bbs", bbsRepository.save(bbs));
    return map;
  }

  @Override
  public Map<String, Object> read(Long index) {
    map.put("bbs", bbsRepository.findById(index));
    return map;
  }

  @Override
  public Map<String, Object> list(int pageNum) {
    map.put("list", bbsRepository.list(pageNum));
    map.put("pagnation", pagination.paging(pageNum, bbsRepository.count()));
    return map;
  }

  @Override
  public Map<String, Object> update(Bbs bbs, Long index) {
    Optional<Bbs> check = bbsRepository.findById(index);
    if (check.isPresent()) {
      Bbs origin = check.get();
      bbs.setIdx(origin.getIdx());
      map.put("bbs", bbsRepository.save(bbs));
    }

    return map;
  }

  @Override
  public Map<String, Object> delete(Long index) {
    bbsRepository.deleteById(index);
    return map;
  }

}