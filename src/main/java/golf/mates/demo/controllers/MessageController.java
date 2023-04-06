package golf.mates.demo.controllers;

import golf.mates.demo.entities.Message;
import golf.mates.demo.services.MessageService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
@Slf4j
public class MessageController {
    private final MessageService messageService;


   /* @GetMapping("/{username}/received")
    public ResponseEntity<List<Message>> getReceivedMessagesByUser(@PathVariable("username") @NotBlank String username) {
        return new ResponseEntity<>(messageService.getMessagesReceivedByUser(username), HttpStatus.OK);
    }

    @GetMapping("/{username}/sent")
    public ResponseEntity<List<Message>> getSentMessagesByUser(@PathVariable("username") @NotBlank String username) {
        return new ResponseEntity<>(messageService.getMessagesSentFromUser(username), HttpStatus.OK);
    }*/


}