package app.api.bbs.file;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BbsFile {
  @Id
  @GeneratedValue
  Long idx;
  
}