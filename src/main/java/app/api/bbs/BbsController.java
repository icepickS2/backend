package app.api.bbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.util.Msg;

@RestController
@RequestMapping("/api/bbs")
public class BbsController {

  @Autowired
  BbsService bbsService;

  @Autowired
  Msg map;

  @PostMapping({ "/", "" })
  public Msg create(Bbs bbs) {
    map.put("bbs", bbsService.create(bbs).get("bbs"));
    return map;
  }

  @GetMapping("/list/{pageNum}")
  public Msg list(@PathVariable int pageNum) {
    map.put("page", bbsService.list(pageNum));
    return map;
  }

  @GetMapping("/{index}")
  public Msg read(@PathVariable Long index) {
    map.put("bbs", bbsService.read(index).get("bbs"));
    return map;
  }

  @PutMapping("/{index}")
  public Msg update(@PathVariable Long index, Bbs bbs) {
    map.put("bbs", bbsService.update(bbs, index).get("bbs"));
    return map;
  }

  @DeleteMapping("/{index}")
  public Msg delete(@PathVariable Long index) {
    bbsService.delete(index);
    return map;
  }
}