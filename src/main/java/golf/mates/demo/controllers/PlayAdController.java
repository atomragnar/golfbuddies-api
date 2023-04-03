package golf.mates.demo.controllers;

import golf.mates.demo.entities.PlayAd;
import golf.mates.demo.services.PlayAdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/playad")
@RequiredArgsConstructor
@Slf4j
public class PlayAdController {

    private final PlayAdService playAdService;

    @GetMapping("/playads/{id}")
    public ResponseEntity<PlayAd> getPlayAdById(@PathVariable Long id) {
        return new ResponseEntity<>(playAdService.getPlayAdById(id), HttpStatus.OK);
    }

}