package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@Bean
	ApplicationRunner test() {
		return args -> {
			ValueOperations<String, Object> values = redisTemplate.opsForValue();
			values.set("test", "test");
			values.set("java", "java");
			values.set("c", "c");
		};
	}
}
