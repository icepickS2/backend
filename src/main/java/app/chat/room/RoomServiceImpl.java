package app.chat.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.api.user.User;
import app.chat.msg.Msg;
import app.chat.msg.MsgType;
import app.chat.participant.Participant;
import app.chat.participant.ParticipantRepository;
import app.chat.participant.ParticipantType;
import app.config.Message;

/**
 * RoomServiceImpl
 */
@Service
public class RoomServiceImpl implements RoomService {

  @Autowired
  RoomRepository roomRepository;

  @Autowired
  ParticipantRepository participantRepository;

  @Autowired
  Message message;
  

  public Message create(Room info,User user,List<User> invitees) {
    Room room=roomRepository.save(info);
    Participant host=Participant.builder().room(room).user(user).desc(ParticipantType.HOST.toString()).build();
    participantRepository.save(host);
    for (User invitee : invitees) {
      Participant guest=Participant.builder().room(room).user(invitee).desc(ParticipantType.GUEST.toString()).build();
      participantRepository.save(guest);
    }
    Msg msg = Msg.builder().room(room).user(user).msgType(MsgType.CREATE).msg(MsgType.CREATE.toString()).build();

    message.put("room",room);
    message.put("msg",msg);
    return message;
  }

  @Override
  public Message leave(Room info, User user) {
    return null;
  }

  @Override
  public Message kick(Room info, User user) {
    return null;
  }

  @Override
  public Message check(Room info, User user) {
    return null;
  }
}