package ie.atu.project.Clients;

import ie.atu.project.Entity.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="auth-service", url = "http://localhost:8081")
public interface AuthClient {
    @PostMapping("/login")
    public String makeLogin(Person person);

    @PostMapping("/signup")
    public String makeSignUp(Person person);
}