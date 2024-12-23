package ie.atu.postpage;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="auth-service", url = "http://localhost:8083/auth")
public interface AuthClient {
    @GetMapping("/signedUsername")
    String getSignedUsername();
    @GetMapping("/signedName")
    String getSignedName();
    @GetMapping("/signedEmail")
    String getSignedEmail();
    @GetMapping("/userType")
    boolean isModerator();

    @GetMapping("/user/{username}/id")
    ResponseEntity<Long> getUserIdByUsername(@PathVariable("username") String username);
}
