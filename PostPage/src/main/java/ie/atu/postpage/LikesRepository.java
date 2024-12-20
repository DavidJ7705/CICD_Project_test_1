package ie.atu.postpage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    // Custom methods if needed
    boolean existsByPostIdAndUsername(Long postId, String username);

    void deleteByPostIdAndUsername(Long postId, String username);
}
