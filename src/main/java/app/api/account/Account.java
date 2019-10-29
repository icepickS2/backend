package app.api.account;

import java.util.Date;
import java.util.Set;

import javax.persistence.ElementCollection;
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

import lombok.Data;

@Data
@Entity
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long idx;

  @Email(message = "이메일 양식에 맞춰서 넣어주세요.")
  private String email; // 예비

  @Pattern(regexp = "\\S{4,8}", message = "비밀번호를 4~8자로 입력해주세요.")
  @JsonIgnore
  private String pw;

  @ElementCollection(fetch = FetchType.EAGER)
  @Enumerated(EnumType.STRING)
  private Set<AccountRole> role;

  @JsonIgnore
  private String platform;
  @JsonIgnore
  private String token;

  @Pattern(regexp = "\\S{4,8}", message = "닉네임을 4~8자로 입력해주세요.")
  private String nickname;

  private Date createAt;
  private Date modifyAt;
}