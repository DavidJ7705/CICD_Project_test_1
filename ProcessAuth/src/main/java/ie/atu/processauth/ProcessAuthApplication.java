package ie.atu.processauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ProcessAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcessAuthApplication.class, args);
    }

}
