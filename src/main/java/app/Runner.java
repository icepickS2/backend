package app;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import app.api.account.Platform;
import app.api.account.Role;
import app.api.account.Account;
import app.api.account.AccountRepository;

@Component
public class Runner implements ApplicationRunner {

  @Autowired
  AccountRepository accountRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    for (Integer i = 0; i < 10; i++) {
      //@formatter:off
      accountRepository.save(
        Account.builder()
          .email(i + "@a.a")
          .password(passwordEncoder.encode("1111"))
          .roles(new HashSet<Role>(Arrays.asList(Role.USER)))
          .platform(Platform.ICEPICK)
        .build()
      );
      //@formatter:on
    }

    System.out.println(passwordEncoder.encode("icepick"));
  }
}