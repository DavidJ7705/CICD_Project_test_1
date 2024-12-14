package ie.atu.processauth;


import org.hibernate.metamodel.model.domain.ListPersistentAttribute;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class PersonController {
    private final List<Person> personList = new ArrayList<>();

    @GetMapping("/login/{userId}")
    public Person getPerson(@PathVariable String userId){
        System.out.println("Child auth service fetching person with id: " + userId);
        return personList.stream()
                .filter(person -> person.getUserId().equals(userId))
                .findFirst()
                .orElse(null);

    }

    @PostMapping("/signup")
    public String addPerson(@RequestBody Person person){
        personList.add(person);
        System.out.println("Person created successfully: "+ person.getName());
        return "Person added successfully";
    }



}
