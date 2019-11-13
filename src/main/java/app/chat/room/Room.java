package app.chat.room;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import app.api.account.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idx")
@Document
public class Room {
  @Id
  private Long idx;
  private String title;
  private String desc;
  

  private Account user;
}