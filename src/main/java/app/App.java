package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	// @Autowired
	// RedisTemplate<String,Object> redisTemplate;

	// @Bean
	// ApplicationRunner test() {
	// return args -> {
	// ValueOperations<String, Object> values = redisTemplate.opsForValue();
	// values.set("test", "test");
	// values.set("java", "java");
	// values.set("c", "c");
	// };
	// }
}
