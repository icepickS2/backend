package app.api.account;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idx")
@Entity
public class Account {

  @Id
  @GeneratedValue
  private Long idx;
  private String email;
  private String password;

  private String name;
  private String imgUrl;

  @ElementCollection(fetch = FetchType.EAGER)
  @Enumerated(EnumType.STRING)
  @Column(name = "roles")
  @Builder.Default
  private Set<Role> roles = new HashSet<>();

  @Enumerated(EnumType.STRING)
  private Platform platform;
  private String platformToken;
}