package golf.mates.demo.repositories;

import golf.mates.demo.entities.PlayAdRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayAdRequestRepository extends JpaRepository<PlayAdRequest, Long> {
}