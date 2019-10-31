package app.embedded;

import java.time.LocalDateTime;

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
  private LocalDateTime createAt;
  @LastModifiedDate
  private LocalDateTime modifyAt;
}