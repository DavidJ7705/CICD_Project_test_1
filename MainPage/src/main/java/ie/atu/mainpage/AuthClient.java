package ie.atu.mainpage;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
