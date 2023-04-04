package golf.mates.demo.repositories;

import golf.mates.demo.entities.PlayAdSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PlayAdSlotRepository extends JpaRepository<PlayAdSlot, Long> {
    List<PlayAdSlot> findByPlayer_Id(Long id);



}