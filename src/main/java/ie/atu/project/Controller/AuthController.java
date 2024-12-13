package ie.atu.project.Controller;


import ie.atu.project.Clients.AuthClient;
import ie.atu.project.Entity.Person;
import ie.atu.project.Services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/person")
@RestController
public class AuthController {

    private final AuthService authService;
    private final AuthClient authClient;

    public AuthController(AuthService authService, AuthClient authClient) {
        this.authService = authService;
        this.authClient = authClient;
    }

    @GetMapping("/login/{userId}")
    public ResponseEntity<?> getPerson(@PathVariable String userId){
        if(userId.length()>5||userId.isBlank()){
            return ResponseEntity.badRequest().body("UserId is invalid");
        }
        Person person = authService.getPersonByUserId(userId);
        if(person == null){
            return ResponseEntity.notFound().build();
        }
        String details = authClient.makeLogin(person);
        System.out.println(details);
        return ResponseEntity.ok(person);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> getSignUp(@RequestBody Person person){
        if(person.getUserId().length() > 5 || person.getUserId().isBlank()){
            return ResponseEntity.badRequest().body("UserId is invalid");
        }

        Person existPerson = authService.getPersonByUserId(person.getUserId());
        if(existPerson == null){
            return ResponseEntity.notFound().build();
        }
        String details = authClient.makeSignUp(person);
        System.out.println(details);
        return ResponseEntity.ok(person);
    }
}
