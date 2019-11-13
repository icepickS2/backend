package app.chat.msg;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import app.api.account.Account;
import app.chat.room.Room;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(of = "idx")
@Document
public class Msg {
  @Id
  private Long idx;

  private Room room;
  private Account user;

  private MsgType msgType;
  private String msg;
}