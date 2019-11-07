package app;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import app.api.user.Platform;
import app.api.user.Role;
import app.api.user.User;
import app.api.user.UserService;

@SuppressWarnings("all")
@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Bean
	public ApplicationRunner applicationRunner() {
		return args -> {
			// User user = new User();
			// user.setEmail("ragnarokdust@gmail.com");
			// user.setPassword("1111");
			// user.setName("대현짱짱맨");
			// user.getRole().add(Role.USER);
			// user.setPlatform(Platform.local);
			// Map<String, Object> map = userService.create(user);
			// System.out.println(map);

			System.out.println(passwordEncoder.encode("icepick"));
			System.out.println(passwordEncoder.encode("icepick_admin"));
			System.out.println(passwordEncoder.encode("1111"));
		};
	}
}