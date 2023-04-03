package golf.mates.demo.controllers;

import golf.mates.demo.entities.PlayAdSlot;
import golf.mates.demo.services.PlayAdSlotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/playadslot")
@RequiredArgsConstructor
@Slf4j
public class PlayAdSlotController {

    private final PlayAdSlotService playAdSlotService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PlayAdSlot>> findBookedSlotsByUserId(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(playAdSlotService.findBookedSlotsByUserId(userId), HttpStatus.OK);
    }

}