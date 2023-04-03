package golf.mates.demo.services;

import golf.mates.demo.entities.Conversation;
import golf.mates.demo.repositories.ConversationRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.hibernate.Hibernate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ConversationService {
    private final ConversationRepository conversationRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Conversation findConversationById(Long id) {
        Conversation conversation = conversationRepository.findById(id).orElse(null);
        if (conversation != null) {
            // Begin a transaction
            entityManager.getTransaction().begin();

            // Initialize the conversation's messages within the transaction
            Hibernate.initialize(conversation.getMessages());

            // Commit the transaction
            entityManager.getTransaction().commit();
        }
        return conversation;
    }


    /*public Conversation findConversationById(Long conversationId) {
        return conversationRepository.findById(conversationId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }*/
}