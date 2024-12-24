package ie.atu.profilepage;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient(name="main-page-service", url = "http://localhost:8080/main")
public interface MainClient {

    @GetMapping("/getProfileCourse")
    public String getProfileCourse();

    @GetMapping("/SignUpCourses")
    List<Map<String, String>> getSignUpCourses();


}
