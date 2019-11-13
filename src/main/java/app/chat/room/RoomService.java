package app.chat.room;

import java.util.List;

import app.api.account.Account;
import app.config.Message;

/**
 * RoomService
 */
public interface RoomService {

  public Message create(Room info,Account user,List<Account> invitees);
  public Message leave(Room info, Account user);

  //추후
  public Message kick(Room info, Account user);
  public Message check(Room info, Account user);
}