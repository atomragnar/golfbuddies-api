package golf.mates.demo.controllers;

import golf.mates.demo.dtos.responses.GolfClubResponseDto;
import golf.mates.demo.entities.GolfClub;
import golf.mates.demo.services.GolfClubService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/golfclub")
@RequiredArgsConstructor
@Slf4j
public class GolfClubController {

    private final GolfClubService golfClubService;

    @GetMapping("/all")
    public ResponseEntity<List<GolfClub>> getAllGolfClubs() {
        return new ResponseEntity<>(golfClubService.getAllGolfClubs(), HttpStatus.OK);
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<List<GolfClub>> getGolfClubByLocationId(@PathVariable("locationId") @Positive Long locationId) {
        return new ResponseEntity<>(golfClubService.findGolClubByLocationId(locationId), HttpStatus.OK);
    }


        @GetMapping("/select/list")
        public ResponseEntity<List<GolfClubResponseDto>> getAllGolfClubsAsDto() {
            return new ResponseEntity<>(golfClubService.getAllClubsAsDto(), HttpStatus.OK);
        }



}