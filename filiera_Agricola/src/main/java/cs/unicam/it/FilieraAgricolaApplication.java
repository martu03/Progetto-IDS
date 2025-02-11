package cs.unicam.it;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FilieraAgricolaApplication {
    public static void main(String[] args) {
        SpringApplication.run(FilieraAgricolaApplication.class, args);
    }
}