package app.api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.api.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
