package ie.atu.processauth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    // Custom method to find a person by username
    Optional<Person> findByUserName(String userName);
}

