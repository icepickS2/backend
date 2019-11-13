package app.api.user;

import java.util.HashSet;
import java.util.Set;

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
@Entity(name="users")
public class User {

  @Id
  @GeneratedValue
  private Long idx;
  private String email;  
  private String password;
  
  @ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
  private Set<Role> roles = new HashSet<>();
}