package app.chat.room;

import java.util.List;

import app.api.user.User;
import app.config.Message;

/**
 * RoomService
 */
public interface RoomService {

  public Message create(Room info,User user,List<User> invitees);
  public Message leave(Room info, User user);

  //추후
  public Message kick(Room info, User user);
  public Message check(Room info, User user);
}