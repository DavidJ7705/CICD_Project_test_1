package ie.atu.profilepage;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

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
}