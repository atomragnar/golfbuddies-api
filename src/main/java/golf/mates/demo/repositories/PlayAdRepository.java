package golf.mates.demo.repositories;

import golf.mates.demo.entities.PlayAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


public interface PlayAdRepository extends JpaRepository<PlayAd, Long> {




}
