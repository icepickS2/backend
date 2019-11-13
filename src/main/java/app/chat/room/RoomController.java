package app.chat.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import app.api.user.User;
import app.config.Message;

@Controller
public class RoomController {

  @Autowired
  private SimpMessageSendingOperations msgTemplate;

  @Autowired
  private Message message;

  @Autowired
  RoomService roomService;

  @MessageMapping("/room")
  public void create(Room info, List<User> invitees) {
    User inviter=(User)SecurityContextHolder.getContext().getAuthentication();
    message = roomService.create(info, inviter, invitees);
    msgTemplate.convertAndSend("/sub/room/"+message.get("room"), message);
  }
}
