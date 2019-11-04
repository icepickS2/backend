package app.api.bbs;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

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
public class Bbs {
  @Id
  @GeneratedValue
  private Long idx;
  @JoinColumn(name = "account_idx")
  // @ManyToOne(fetch = FetchType.LAZY)
  // Account account;
  private String title;
  private String content;

  @Embedded
  private EmbeddedDate date;
}