package golf.mates.demo.services;

import golf.mates.demo.entities.Message;
import golf.mates.demo.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MessageService {
    private final MessageRepository messageRepository;


    public List<Message> getMessagesSentFromUser(String username) {

        // TODO: 2023-03-28 behöver addera validering av username to be sure.
        List<Message> messageList = messageRepository.findBySender_UsernameIgnoreCaseOrderByCreatedDateDesc(username);

        if (!messageList.isEmpty()) {
            return messageList;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

    public List<Message> getMessagesReceivedByUser(String username) {

        // TODO: 2023-03-28 behöver addera validering av username to be sure.
        List<Message> messageList = messageRepository.findByReceiver_UsernameIgnoreCaseOrderByCreatedDateDesc(username);

        if (!messageList.isEmpty()) {
            return messageList;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

}