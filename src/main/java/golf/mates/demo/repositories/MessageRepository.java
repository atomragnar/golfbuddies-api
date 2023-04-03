package golf.mates.demo.repositories;

import golf.mates.demo.entities.Conversation;
import golf.mates.demo.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByConversation_Id(Long id);



}