package ie.atu.processauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {
    private final PersonRepository personRepository;
    public AuthService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    private List<Person> personList = new ArrayList<>();

    public String signUp(String name, String userName, String email, String password, int courseId) {
        // Check if username already exists
        Optional<Person> existingPerson = personRepository.findByUserName(userName);
        if (existingPerson.isPresent()) {
            return "Username already exists!";
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
            return "Login successful! Welcome, " + userName + "!";
        } else if (person.getUserType() == 2) {
            return "Login successful! Welcome, Moderator!";
        } else {
            return "Unknown user role!";
        }
    }
}
