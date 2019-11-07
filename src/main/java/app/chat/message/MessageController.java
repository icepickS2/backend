package app.chat.message;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

  @PostMapping
  @SendTo()
  public void name() {

  }
}