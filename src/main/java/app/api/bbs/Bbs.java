package app.api.bbs;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import app.api.account.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "idx")
@NoArgsConstructor
@AllArgsConstructor
public class Bbs {
  @Id
  @GeneratedValue
  Long idx;
  @JoinColumn(name = "account_idx")
  // @ManyToOne(fetch = FetchType.LAZY)
  // Account account;
  private String title;
  private String content;

  private LocalDateTime createAt;
  private LocalDateTime modifyAt;
}