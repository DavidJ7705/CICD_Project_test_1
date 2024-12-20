package ie.atu.postpage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    // Custom methods if needed
}
