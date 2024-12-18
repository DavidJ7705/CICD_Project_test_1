package ie.atu.processauth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Sign-up endpoint
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Person person) {
        String response = authService.signUp(person.getName(), person.getUserName(), person.getEmail(), person.getPassword());
        return ResponseEntity.ok(response);
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String response = authService.login(loginRequest.getUserName(), loginRequest.getPassword());
        return ResponseEntity.ok(response);
    }
}
