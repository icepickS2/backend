package app.api.sign;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sign")
public class SignController {

  @PostMapping("/in")
  public void in() {
    
  }

  @PostMapping("/up")
  public void up() {

  }

  @PostMapping("/out")
  public void out() {

  }
}