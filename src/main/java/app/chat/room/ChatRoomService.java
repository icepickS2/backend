package app.chat.room;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import app.chat.message.ChatMessage;

@Service
public class ChatRoomService {

  @Autowired
  private SimpMessageSendingOperations messagingTemplate;
  
  @Autowired
  private ChatRoomRepository chatRoomRepository;

  // message를 방에 뿌려줌 
  public void recieveChat(ChatMessage message) {
    Optional<ChatRoom> check = chatRoomRepository.findById(message.getRoomId());
    if (!check.isPresent()) return;
    ChatRoom room = check.get();
    // room.getUsers().contains(room.);
    messagingTemplate.convertAndSend("/sub/"+message.getRoomId(), message);
  }
}