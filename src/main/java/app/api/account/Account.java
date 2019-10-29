package app.api.account;

import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long idx;

  @Email(message = "이메일 양식에 맞춰서 넣어주세요.")
  private String email;

  @Pattern(regexp = "\\S{4,8}", message = "비밀번호를 4~8자로 입력해주세요.")
  @JsonIgnore
  private String pw;

  @ElementCollection(fetch = FetchType.EAGER)
  @Enumerated(EnumType.STRING)
  private Set<AccountRole> role;

  @JsonIgnore
  private String platform;
  private String token;

  @Pattern(regexp = "\\S{4,8}", message = "닉네임을 4~8자로 입력해주세요.")
  private String nickname;

  @Embedded
  EmbeddedDate embeddedDate;
}