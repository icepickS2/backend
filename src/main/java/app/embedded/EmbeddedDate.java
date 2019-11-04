package app.embedded;



import java.util.Date;

import javax.persistence.Embeddable;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddedDate {
  @CreatedDate
  private Date createAt;
  @LastModifiedDate
  private Date modifyAt;
}