package ie.atu.profilepage;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class ProfilePageService {
    private AuthClient authClient;
    private MainClient mainClient;

    public ProfilePageService(AuthClient authClient, MainClient mainClient) {
        this.authClient = authClient;
        this.mainClient = mainClient;
    }
    public Map<String, String> getProfileInfo() {
        String signedUsername = authClient.getSignedUsername();  // Get signed-in user's name
        String signedName = authClient.getSignedName();
        String signedEmail = authClient.getSignedEmail();
        String signedCourse = mainClient.getProfileCourse();
        // You might also want to call another endpoint or method to get the user's email if needed

        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("userName", signedUsername);
        userInfo.put("Name", signedName);
        userInfo.put("Email", signedEmail);
        userInfo.put("Course", signedCourse);
        return userInfo;
    }
}
