package be.team4.parkshark;

import be.team4.parkshark.secret.Secret;
import be.team4.parkshark.secret.SecretRepository;
import be.team4.parkshark.user.User;
import be.team4.parkshark.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class JpaSkeletonApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaSkeletonApplication.class, args);
    }

    @Bean
    @Profile(value = "dev")
    public CommandLineRunner createDummyData(SecretRepository secretRepository, UserRepository userRepository) {
        return (args) -> {
            userRepository.save(new User("freddy"));
            User betty007 = userRepository.save(new User("betty007"));
            User mazzzz = userRepository.save(new User("Mazzzz"));

            secretRepository.save(new Secret("I am the forgotten king of Belgium", mazzzz));
            secretRepository.save(new Secret("I am actually a secret spy", betty007));
            secretRepository.save(new Secret("Today I stole my colleague's lunch", betty007));
        };
    }

}
