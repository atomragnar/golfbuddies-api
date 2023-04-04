package golf.mates.demo.controllers;

import golf.mates.demo.dtos.request.PlayAdRegistrationDto;
import golf.mates.demo.dtos.request.UserRegistrationDto;
import golf.mates.demo.entities.PlayAd;
import golf.mates.demo.services.PlayAdService;
import golf.mates.demo.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playad/")
@RequiredArgsConstructor
@Slf4j
public class PlayAdController {
    private final PlayAdService playAdService;
    @PostMapping("register")
    public ResponseEntity<HttpStatus> addNewUser(@RequestBody PlayAdRegistrationDto playAdRegistrationDto) {
        System.out.println(playAdRegistrationDto);
        playAdService.registerNewAd(playAdRegistrationDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    public  ResponseEntity<List<PlayAd>> getAds(){
        return new ResponseEntity<>(playAdService.getAllAds(), HttpStatus.OK);
    }
}