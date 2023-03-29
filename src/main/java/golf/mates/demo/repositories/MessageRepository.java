package golf.mates.demo.repositories;

import golf.mates.demo.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("select m from Message m where upper(m.sender.username) = upper(?1) order by m.createdDate DESC")
    List<Message> findBySender_UsernameIgnoreCaseOrderByCreatedDateDesc(String username);

    @Query("select m from Message m where upper(m.receiver.username) = upper(?1) order by m.createdDate DESC")
    List<Message> findByReceiver_UsernameIgnoreCaseOrderByCreatedDateDesc(String username);




}