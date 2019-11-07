package app.api.user;

import java.util.HashSet;
import java.util.Set;

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
	private Set<Role> role = new HashSet<Role>();

	@Enumerated(EnumType.STRING)
	private Platform platform;
	private String platformToken;

	@Embedded
	private EmbeddedDate date;
}
