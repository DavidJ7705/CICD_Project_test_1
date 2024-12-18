package ie.atu.mainpage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MainPageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainPageApplication.class, args);
    }

}
