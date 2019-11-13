package app;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import app.api.user.Role;
import app.api.user.User;
import app.api.user.UserRepository;

@Component
public class Runner implements ApplicationRunner {

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    for (Integer i = 0; i < 10; i++) {
      //@formatter:off
      userRepository.save(
        User.builder()
          .email(i + "@a.a")
          .password(passwordEncoder.encode("1111"))
          .roles(new HashSet<Role>(Arrays.asList(Role.USER)))
        .build()
      );
      //@formatter:on
    }

    System.out.println(passwordEncoder.encode("icepick"));
  }
}