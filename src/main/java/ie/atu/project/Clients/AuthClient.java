package ie.atu.project.Clients;

import ie.atu.project.Entity.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="auth-service", url = "http://localhost:8083/auth")
public interface AuthClient {
    @GetMapping("/login/{userId}")
    default Person getPerson(@PathVariable String userId) {
        Person person = getPersonInternal(userId);
        System.out.println("Feign client fetching person with userId: " + person);
        return person;
    }


    @GetMapping("/login/{userId}")
    Person getPersonInternal(@PathVariable String userId);

    @PostMapping("/signup")
    void addPerson(Person person);
}