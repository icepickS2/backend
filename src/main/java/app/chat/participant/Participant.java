package app.chat.participant;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;
import app.api.account.Account;
import app.chat.room.ChatRoom;
import lombok.Getter;
import lombok.Setter;

//@ Data 쓰니까 hashCode() 함수에서 순환참조남;;
@Entity
@Setter
@Getter
public class Participant {
  @Id
  @GeneratedValue
  private Long id;
  @ManyToOne
  private Account account;
  @ManyToOne
  private ChatRoom chatRoom;
  @Enumerated(EnumType.STRING)
  private Authority authority = Authority.ROLE_GUEST;

  public enum Authority {
    ROLE_HOST, ROLE_GUEST;
  }

  @JsonBackReference
  public ChatRoom getChatRoom() {
    return chatRoom;
  }
}