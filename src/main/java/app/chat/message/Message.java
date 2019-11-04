package app.chat.message;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import app.api.user.User;
import app.chat.room.Room;

@Document
public class Message {
  @Id
  Long idx;
  Room room;
  User user;
  String msg;
}