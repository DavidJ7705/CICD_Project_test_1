package ie.atu.processauth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignUpController {
    @PostMapping
    public String makeSignUp(@RequestBody Person person){
        return "signup Microservice has been called";
    }
}