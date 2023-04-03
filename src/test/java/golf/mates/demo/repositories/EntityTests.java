package golf.mates.demo.repositories;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import golf.mates.demo.entities.Conversation;
import golf.mates.demo.entities.Message;
import golf.mates.demo.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class EntityTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private MessageRepository messageRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testUser() {
        User user = new User();
        user.setUsername("testuser");
        userRepository.save(user);

        User foundUser = userRepository.findByUsernameIgnoreCase("testuser").get();
        assertThat(foundUser.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void testConversation() {
        User user1 = new User();
        user1.setUsername("testuser1");
        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("testuser2");
        userRepository.save(user2);

        Conversation conversation = new Conversation();
        conversation.setUser1(user1);
        conversation.setUser2(user2);
        conversationRepository.save(conversation);

        Conversation foundConversation = conversationRepository.findByUser1_IdAndUser2_Id(user1.getId(), user2.getId()).get();
        assertThat(foundConversation.getUser1().getUsername()).isEqualTo(user1.getUsername());
        assertThat(foundConversation.getUser2().getUsername()).isEqualTo(user2.getUsername());
    }

    @Test
    public void testMessage() {
        User sender = new User();
        sender.setUsername("testuser1");
        userRepository.save(sender);

        User recipient = new User();
        recipient.setUsername("testuser2");
        userRepository.save(recipient);

        Conversation conversation = new Conversation();
        conversation.setUser1(sender);
        conversation.setUser2(recipient);
        conversationRepository.save(conversation);

        Message message = new Message();
        message.setSender(sender);
        message.setRecipient(recipient);
        message.setConversation(conversation);
        message.setContent("Hello, world!");
        messageRepository.save(message);

        List<Message> messages = messageRepository.findByConversation_Id(conversation.getId());
        assertThat(messages.size()).isEqualTo(1);
        assertThat(messages.get(0).getContent()).isEqualTo("Hello, world!");
    }

}