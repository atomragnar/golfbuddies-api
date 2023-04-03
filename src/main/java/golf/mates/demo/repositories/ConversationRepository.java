package golf.mates.demo.repositories;

import golf.mates.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import golf.mates.demo.entities.Conversation;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    @Query("select c from Conversation c LEFT JOIN FETCH c.messages where c.user1.id = ?1 and c.user2.id = ?2")
    Optional<Conversation> findByUser1_IdAndUser2_Id(Long id, Long id1);


    //Optional<Conversation> findByUser1_IdAndUser2_Id(Long id, Long id1);

  /*  @Query("SELECT c FROM Conversation c LEFT JOIN FETCH c.messages WHERE c.user1 = ?1 AND c.user2 = ?2")
    Optional<Conversation> findByUser1_IdAndUser2_Id(User user1, User user2);*/




    

}