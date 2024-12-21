package ie.atu.profilepage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ProfilePageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProfilePageApplication.class, args);
    }

}
