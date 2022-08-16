package api.v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CodestatesSoloProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodestatesSoloProjectApplication.class, args);
    }

}
