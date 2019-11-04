package app.api.user;

<<<<<<< HEAD
import java.util.Set;
=======
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
>>>>>>> origin/master

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

<<<<<<< HEAD
=======
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

>>>>>>> origin/master
import app.embedded.EmbeddedDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "USERS")
@NoArgsConstructor
@EqualsAndHashCode(of = "idx")
public class User {

	@Id
	@GeneratedValue
	protected Long idx;
	private String email;
	private String password;

	private String name;
	private String imageUrl;

	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	@Column(name = "roles")
	private Set<Role> role;

	@Enumerated(EnumType.STRING)
	private Platform platform;
	private String platformToken;

	@Embedded
	private EmbeddedDate date;
}
