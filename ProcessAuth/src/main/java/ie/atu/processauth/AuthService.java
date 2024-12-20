package ie.atu.processauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {
    private final PersonRepository personRepository;
    private String SignedUsername;
    private String SignedName;
    private String SignedEmail;
    public AuthService(PersonRepository personRepository) {
        this.personRepository = personRepository;
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
}
