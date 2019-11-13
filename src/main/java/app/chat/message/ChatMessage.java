package app.chat.message;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import app.api.account.Account;
import lombok.Data;

@Data
@Entity
public class ChatMessage {
  public enum Type {
    QUIT, CHAT, JOIN
  }

  @Id
  @GeneratedValue
  private Long id;
  @Enumerated(EnumType.STRING)
  private Type type;
  private Long roomId;
  @ManyToOne
  private Account sender;
  private String message;
}
