package golf.mates.demo.services;

import golf.mates.demo.entities.Conversation;
import golf.mates.demo.entities.Message;
import golf.mates.demo.repositories.MessageRepository;

import lombok.extern.slf4j.Slf4j;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
@Slf4j
public class MessageService {
    private final MessageRepository messageRepository;
    private final ConversationService conversationService;

    public MessageService(MessageRepository messageRepository, ConversationService conversationService) {
        this.messageRepository = messageRepository;
        this.conversationService = conversationService;
    }


    public Message save(Message message) {
        return messageRepository.save(message);
    }

    public Message createMessage(Long conversationId, Message message) {
        // Find the conversation by ID
        Conversation conversation = conversationService.findConversationById(conversationId);

        // Add the message to the conversation's messages collection
        conversation.getMessages().add(message);

        // Set the conversation on the message
        message.setConversation(conversation);

        // Save the message and return it
        return messageRepository.save(message);
    }





}