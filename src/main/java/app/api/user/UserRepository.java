package app.api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import app.api.user.User;

/**
 * 
 * @author Kristijan Georgiev
 * 
 *         UserRepository with custom methods for finding a User by username or
 *         email
 *
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u where username = :username")
	Optional<User> findByUsername(String username);

	@Query("select u from User u where email = :email")
	Optional<User> findByEmail(String email);

}
