package ie.atu.postpage;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="auth-service", url = "http://localhost:8083/auth")
public interface AuthClient {
    @GetMapping("/signedUser")
    String getSignedUser();
}
