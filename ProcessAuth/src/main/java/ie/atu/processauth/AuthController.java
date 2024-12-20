package ie.atu.processauth;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;

@RequestMapping("/auth")
@RestController
@CrossOrigin(origins = "http://localhost:8083") // Enable CORS for this controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Sign-up endpoint
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Person person) {
        String response = authService.signUp(person.getName(), person.getUserName(), person.getEmail(), person.getPassword(), person.getCourseId());
        if (response.startsWith("User signed up successfully!")) {
            return ResponseEntity.ok("success");  // Just return success
        } else {
            return ResponseEntity.ok(response); // Return failure message
        }
    }


    // Login endpoint with redirect
    // Login endpoint with simplified response
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String response = authService.login(loginRequest.getUserName(), loginRequest.getPassword());
        // Check if login was successful
        if (response.startsWith("Login successful")) {
            return ResponseEntity.ok("success");  // Just return success
        } else {
            return ResponseEntity.ok(response); // Return failure message
        }

    }
    @GetMapping("/userCourse")
    public Long getCourseIdByUsername() {
        // Searching for the person by their username
        return authService.getCourseIdByUsername();
    }
    @GetMapping("/signedUsername")
    public String getSignedUsername() {
        return authService.getSignedUsername();
    }

    @GetMapping("/signedName")
    public String getSignedName() {
        return authService.getSignedName();
    }

    @GetMapping("/signedEmail")
    public String getSignedEmail() {
        return authService.getSignedEmail();
    }


    @GetMapping("/fetchcourses")
    public ResponseEntity<List<Map<String, String>>> fetchAllCourses() {
        List<Map<String, String>> courses = authService.fetchCourses();
        return ResponseEntity.ok(courses);
    }

}
