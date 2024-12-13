package ie.atu.processauth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @PostMapping
    public String makeLogin(@RequestBody Person person){
        return "Login Microservice has been called";
    }
}