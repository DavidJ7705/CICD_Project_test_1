package ie.atu.project.Services;

import ie.atu.project.Entity.Person;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    // Placeholder method to save a person (add to Db in the next two weeks)
    public void savePerson(Person person) {
        System.out.println("Person saved: " + person);
    }

    // Placeholder method to retrieve a person by employeeId
    public Person getPersonByUserId(String userId) {
        // fetch data from a database in future lab
        // For simplicity, we return a dummy person here
        return new Person();
    }

}
