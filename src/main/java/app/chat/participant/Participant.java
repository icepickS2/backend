package app.chat.participant;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import app.api.user.User;
import app.chat.room.Room;

@Document
public class Participant {
  @Id
  Long idx;

  Room room;

  User user;

  String option;
}