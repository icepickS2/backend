package app.chat.participant;

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
public class Participant {
  @Id
  private Long idx;
  
  private Room room;
  private Account user;
  private String desc;
}