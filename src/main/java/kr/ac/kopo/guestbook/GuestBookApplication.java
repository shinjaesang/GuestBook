package kr.ac.kopo.guestbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GuestBookApplication {

    public static void main(String[] args) {

        SpringApplication.run(GuestBookApplication.class, args);
    }

}
