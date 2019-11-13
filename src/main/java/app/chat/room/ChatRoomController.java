package app.chat.room;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import app.api.account.Account;
import app.chat.participant.Participant;
import app.chat.participant.ParticipantRepository;
import app.chat.participant.Participant.Authority;
import app.security.sign.Sign;

@RestController
@RequestMapping("chat/room")
public class ChatRoomController {

  @Autowired
  private ChatRoomRepository chatRoomRepository;
  @Autowired
  private ParticipantRepository participantRepository;

  // 채팅방 목록 보기
  @GetMapping("/list")
  public Map<String, Object> list() {
    Map<String, Object> map = new HashMap<>();
    map.put("list", chatRoomRepository.findAll());
    return map;
  }

  // 채팅방 id로 찾기
  @GetMapping("/find/{id}")
  public Map<String, Object> find(@PathVariable Long id) {
    Map<String, Object> map = new HashMap<>();
    map.put("room", chatRoomRepository.findById(id));
    return map;
  }
  
  // 채팅방에 참여하기
  @GetMapping("/join/{id}")
  @Transactional
  public void join(@PathVariable Long id) {
    Sign userDetails = (Sign) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Account account = userDetails.getAccount();
    Participant guest = new Participant();
    guest.setAccount(account);
    Optional<ChatRoom> check = chatRoomRepository.findById(id);
    if (check.isPresent()) {
      ChatRoom room = check.get();
      room.getUsers().add(guest);
      guest.setChatRoom(room);
      participantRepository.save(guest);
    }
  }

  // 채팅방 만들기
  @GetMapping("/create/{roomName}")
  @Transactional
  public void create(@PathVariable String roomName) {
    Sign userDetails = (Sign) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Account account = userDetails.getAccount();
    Participant host = new Participant();
    host.setAccount(account);
    host.setAuthority(Authority.ROLE_HOST);

    ChatRoom room = new ChatRoom();
    room.setName(roomName);
    room.getUsers().add(host);
    host.setChatRoom(room);

    participantRepository.save(host);
    chatRoomRepository.save(room);
  }
}