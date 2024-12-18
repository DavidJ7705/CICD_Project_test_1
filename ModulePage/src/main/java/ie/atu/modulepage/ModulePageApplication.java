package ie.atu.modulepage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ModulePageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModulePageApplication.class, args);
    }

}
