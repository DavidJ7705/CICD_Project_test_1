package ie.atu.project.Services;

import ie.atu.project.Clients.AuthClient;
import ie.atu.project.Entity.Person;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthClient authClient;

    public AuthService(AuthClient authClient) {
        this.authClient = authClient;
    }

    public String signUp(Person person) {
        authClient.addPerson(person);
        return "User signed up successfully!";
    }

    public String login(String userId, String password) {
        System.out.println("Authenticating userId: " + userId + ", password: " + password);
        System.out.println("Provided password: " + password);

        Person person = authClient.getPerson(userId);
        System.out.println("Fetched Person: " + person);

        if (person == null) {
            return "User not found!";
        }

        System.out.println("Stored Password: " + person.getPassword());
        System.out.println("Password Comparison Result: " + password.equals(person.getPassword()));

        if (password.trim().equals(person.getPassword().trim())) {
            return "Login successful!";
        }

        return "Invalid credentials!";
    }

}
