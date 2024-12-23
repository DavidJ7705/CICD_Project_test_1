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
            SignedUsername = userName;
            SignedName = person.getName();
            SignedEmail = person.getEmail();
            SignedCourse = person.getCourseId();

            return "Login successful! Welcome, " + SignedUsername + "! Name: "+ SignedName + ", Email: " + SignedEmail;
    }



    public String updateUser(Long userId, Person updatedPerson) {
        Optional<Person> existingPersonOpt = personRepository.findById(userId);
        if (existingPersonOpt.isEmpty()) {
            return "User not found!";
        }

        Person existingPerson = existingPersonOpt.get();

        // Update fields only if they are provided in the request
        if (updatedPerson.getName() != null) {
            existingPerson.setName(updatedPerson.getName());
        }
        if (updatedPerson.getUserName() != null) {
            existingPerson.setUserName(updatedPerson.getUserName());
        }
        if (updatedPerson.getEmail() != null) {
            existingPerson.setEmail(updatedPerson.getEmail());
        }
        if (updatedPerson.getPassword() != null) {
            existingPerson.setPassword(updatedPerson.getPassword());
        }
        if (updatedPerson.getCourseId() != 0) { // Assuming courseId cannot be null
            existingPerson.setCourseId(updatedPerson.getCourseId());
        }

        personRepository.save(existingPerson);

        return "User updated successfully!";
    }

    public String updateUserDetails(String username, String name, String email, int courseId) {
        Person person = personRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (name != null) person.setName(name);
        if (email != null) person.setEmail(email);
        if (courseId != 0) person.setCourseId(courseId);

        personRepository.save(person);
        return "User updated successfully!";
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

    public boolean isModerator(){
        Optional<Person> signedPerson = personRepository.findByUserName(SignedUsername);
        return signedPerson.map(person -> person.getUserType() == 2).orElse(false);
    }

}
