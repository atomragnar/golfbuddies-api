package golf.mates.demo.controllers;

import golf.mates.demo.dtos.request.PlayAdRequestDto;
import golf.mates.demo.entities.PlayAd;
import golf.mates.demo.services.PlayAdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/playad")
@RequiredArgsConstructor
@Slf4j
public class PlayAdController {

    private final PlayAdService playAdService;

    @PostMapping("/new")
    public ResponseEntity<PlayAd> createNewPlayAd(@RequestBody PlayAdRequestDto playAdRequestDto) {
        return new ResponseEntity<>(playAdService.createNewPlayAd(playAdRequestDto), HttpStatus.OK);
    }

    @GetMapping("/{playAdId}")
    public ResponseEntity<PlayAd> getPlayAdById(@PathVariable Long playAdId) {
        return new ResponseEntity<>(playAdService.getPlayAdById(playAdId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlayAd>> getAllPlayAds() {
        return null;
    }


    @GetMapping("/list/{userId}")
    public ResponseEntity<List<PlayAd>> getAllPlayAdsByUser(@PathVariable("userId") Long userId) {
        return null;
    }


    @GetMapping("/suggestions/{userId}")
    public ResponseEntity<List<PlayAd>> getSuggestedPlayAds(@PathVariable("userId") Long userId) {
        return null;
    }

    @GetMapping("/booked/{userId}")
    public ResponseEntity<List<PlayAd>> getBookedPlayAds(@PathVariable("userId") Long userId) {
        return null;
    }

    @GetMapping("/created/{userId}")
    public ResponseEntity<List<PlayAd>> getCreatedPlayAds(@PathVariable("userId") Long userId) {
        return null;
    }


}