package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
    userRepository.save(User.builder().email("a@a.a").password(passwordEncoder.encode("1111")).build());
    userRepository.save(User.builder().email("b@b.b").password(passwordEncoder.encode("1111")).build());
    userRepository.save(User.builder().email("c@c.c").password(passwordEncoder.encode("1111")).build());
    userRepository.save(User.builder().email("d@d.d").password(passwordEncoder.encode("1111")).build());

    System.out.println(passwordEncoder.encode("icepick"));
  }
}