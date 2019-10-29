package app.api.bbs.file;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import app.embedded.EmbeddedDate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "idx")
@NoArgsConstructor
@AllArgsConstructor
public class BbsFile {
  @Id
  @GeneratedValue
  Long idx;

  @Embedded
  EmbeddedDate embeddedDate;
}