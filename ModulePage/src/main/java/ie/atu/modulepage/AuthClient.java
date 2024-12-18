package ie.atu.modulepage;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="auth-service", url = "http://localhost:8083/auth")
public interface AuthClient {

    @GetMapping("/signedUser")
    String getSignedUser();
}
