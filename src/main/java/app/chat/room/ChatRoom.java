package app.chat.room;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import app.chat.participant.Participant;
import lombok.Getter;
import lombok.Setter;

//@ Data 쓰니까 hashCode() 함수에서 순환참조남;;
@Entity
@Getter
@Setter
public class ChatRoom {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
  @OneToMany(mappedBy="chatRoom")
  private Set<Participant> users = new HashSet<>();

  @JsonManagedReference
  public Set<Participant> getUsers() {
    return users;
  }
}