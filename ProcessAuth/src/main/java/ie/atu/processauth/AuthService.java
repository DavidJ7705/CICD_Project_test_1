package ie.atu.processauth;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {
    private final PersonRepository personRepository;
    private String SignedUsername;
    private String SignedName;
    private final MainClient mainClient;
    private long SignedCourse;

    private String SignedEmail;
    public AuthService(PersonRepository personRepository, MainClient mainClient) {
        this.personRepository = personRepository;
        this.mainClient = mainClient;
    }

    private List<Person> personList = new ArrayList<>();

    public String signUp(String name, String userName, String email, String password, int courseId) {
        // Check if username already exists
        Optional<Person> existingUsername = personRepository.findByUserName(userName);
        if (existingUsername.isPresent()) {
            return "Username already exists!";
        }
        Optional<Person> existingEmail = personRepository.findByEmail(email);
        if (existingEmail.isPresent()) {
            return "Email already exists!";
        }
        Optional<Person> existingName = personRepository.findByName(name);
        if (existingName.isPresent()) {
            return "Name already exists!";
        }

        // Create and save a new user
        Person newPerson = new Person(name, userName, email, password, courseId);

        personRepository.save(newPerson);

        SignedUsername = userName;
        SignedName = name;
        SignedEmail = email;
        SignedCourse = courseId;


        return "User signed up successfully!";
    }

    public String login(String userName, String password) {
        // Find the user by username
        Optional<Person> optionalPerson = personRepository.findByUserName(userName);
        if (optionalPerson.isEmpty()) {
            return "User not found!";
        }

        Person person = optionalPerson.get();

        // Check the password
        if (!person.getPassword().equals(password)) {
            return "Invalid password!";
        }

        // Check userType to determine the role
        if (person.getUserType() == 1) {
            SignedUsername = userName;
            SignedName = person.getName();
            SignedEmail = person.getEmail();
            SignedCourse = person.getCourseId();

            return "Login successful! Welcome, " + SignedUsername + "! Name: "+ SignedName + ", Email: " + SignedEmail;
        } else if (person.getUserType() == 2) {
            return "Login successful! Welcome, Moderator!";
        } else {
            return "Unknown user role!";
        }

    }

    public String getSignedUsername() {
        return SignedUsername;
    }

    public String getSignedName(){
        return SignedName;
    }

    public String getSignedEmail(){
        return SignedEmail;
    }
    public Long getCourseIdByUsername() {
        return SignedCourse;
    }
    public List<Map<String,String>> fetchCourses(){
        try {
            ResponseEntity<List<Map<String, String>>> response = mainClient.SignUpCourses();
            System.out.println("Courses fetched from MainClient: " + response.getBody());
            return response.getBody();
        } catch (Exception e) {
            System.err.println("Error fetching courses: " + e.getMessage());
            return List.of();
        }
    }

}
