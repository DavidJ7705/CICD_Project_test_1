package ie.atu.profilepage;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="auth-service", url = "http://localhost:8083/auth")
public interface AuthClient {
    @GetMapping("/signedUsername")
    String getSignedUsername();

    @GetMapping("/signedName")
    String getSignedName();

    @GetMapping("/signedEmail")
    String getSignedEmail();
    @GetMapping("/userCourse")
    Long getCourseIdByUsername();

    @PutMapping("/update/{username}")
    String updateUserDetails(@PathVariable ("username") String username, @RequestParam("name") String name,@RequestParam("email") String email,@RequestParam("courseId") int courseId );
}
