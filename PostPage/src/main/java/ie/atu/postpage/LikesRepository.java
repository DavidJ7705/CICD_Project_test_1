package ie.atu.postpage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    // Custom methods if needed
    boolean existsByPostIdAndUsername(Long postId, String username);

    void deleteByPostIdAndUsername(Long postId, String username);
    List<Likes> findByUsername(String username);
}
