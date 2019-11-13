package app.chat.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import app.chat.room.ChatRoomService;

@Service
public class ChatMessageService {
  @Autowired
  private ChatMessageRepository chatMessageRepository;
  @Autowired
  private ChatRoomService chatRoomService;


  // 클라이언트로 부터 채팅을 받음
  @Transactional
  public void recieveChat(ChatMessage message) {
    ChatMessage savedMessage = chatMessageRepository.save(message);
    chatRoomService.recieveChat(savedMessage); // observe
  }
}