package ie.atu.processauth;

import jakarta.validation.Valid;
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
    public ResponseEntity<String> signUp(@Valid @RequestBody Person person) {
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
    public ResponseEntity<String> login(@Valid @RequestBody Person person) {
        String response = authService.login(person.getUserName(), person.getPassword());
        if (response.startsWith("Login successful!")) {
            return ResponseEntity.ok("success");  // Just return success
        } else {
            return ResponseEntity.ok(response); // Return failure message
        }


    }



    //update based on user id
    @PutMapping("/update/{userId}")
    public ResponseEntity<String> updateUser(@Valid @PathVariable Long userId, @Valid @RequestBody Person updatedPerson) {
        String response = authService.updateUser(userId, updatedPerson);
        return ResponseEntity.ok(response);
    }


    //update for the front end
    @PutMapping("/update/{username}")
    public ResponseEntity<String> updateUser(@Valid @PathVariable String username, @Valid @RequestParam String name,@Valid @RequestParam String email,@Valid @RequestParam int courseId ){
        String response = authService.updateUserDetails(username, name, email, courseId);
        return ResponseEntity.ok(response);
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

    @GetMapping("/userType")
    public boolean isModerator(){
        return authService.isModerator();
    }

    @GetMapping("/user/{username}/id")
    public ResponseEntity<Long> getUserIdByUsername(@Valid @PathVariable String username) {
        Long userId = authService.getUserIdByUsername(username);
        return ResponseEntity.ok(userId);
    }


}
