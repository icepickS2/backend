package app.chat.participant;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import app.api.user.User;
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
  private User user;
  private String desc;
}