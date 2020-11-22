package in.ac.bitspilani.webapp;

//import in.ac.bitspilani.webapp.springsecuritycutomlogin.JPAUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableJdbcRepositories(basePackageClasses = JPAUserRepository.class)
@EnableScheduling
public class WebAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebAppApplication.class, args);
    }

}
