package app.chat.message;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import app.api.account.Account;
import app.util.JwtTokenParser;


@Controller
public class ChatMessageController {
  @Autowired
  private ChatMessageService chatMessageService;
  @Autowired
  private JwtTokenParser jwtTokenParser;
  
  // websocket이 정보를 주는 목표점
  @MessageMapping("/") // '/pub/' 로 요청시 이쪽으로
  public void message(ChatMessage message) throws Exception {
    Optional<Account> check = jwtTokenParser.getOwner("minsu"); // 필터 안거쳐서 일단 만든 친구
    if (check.isPresent()) {
      message.setSender(check.get());
      chatMessageService.recieveChat(message);
    }
  }
}