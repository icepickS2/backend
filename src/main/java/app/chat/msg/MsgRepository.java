package app.chat.msg;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MsgRepository extends MongoRepository<Msg,Long>{

  
}