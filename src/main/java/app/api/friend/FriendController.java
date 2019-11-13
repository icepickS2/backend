package app.api.friend;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import app.api.account.Account;
import app.security.sign.Sign;

@RestController
public class FriendController {

  @GetMapping
  public void getFriend() {
    Account account = ((Sign) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
    
  }
}