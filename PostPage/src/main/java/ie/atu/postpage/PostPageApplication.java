package ie.atu.postpage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PostPageApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostPageApplication.class, args);
    }

}
