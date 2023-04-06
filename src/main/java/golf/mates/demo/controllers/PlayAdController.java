package golf.mates.demo.controllers;

import golf.mates.demo.dtos.request.PlayAdRequestDto;
import golf.mates.demo.dtos.responses.PlayAdResponseDto;
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
    public ResponseEntity<HttpStatus> createNewPlayAd(@RequestBody PlayAdRequestDto playAdRequestDto) {
        playAdService.createNewPlayAd(playAdRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
}

    @GetMapping("/{playAdId}")
    public ResponseEntity<PlayAdResponseDto> getPlayAdById(@PathVariable Long playAdId) {
        return new ResponseEntity<>(playAdService.getPlayAdResponseDtoById(playAdId), HttpStatus.OK);
    }


    @DeleteMapping("/{playAdId}")
    public ResponseEntity<HttpStatus> deletePlayAd(@PathVariable Long playAdId) {
        playAdService.deletePlayAd(playAdId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlayAdResponseDto>> getAllPlayAds() {
        return new ResponseEntity<>(playAdService.getAllPlayAds(), HttpStatus.OK);
    }


    @GetMapping("/list/{userId}")
    public ResponseEntity<List<PlayAdResponseDto>> getAllPlayAdsByUser(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(playAdService.getAllPlayAdsByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<PlayAdResponseDto>> getPlayAdsByGolfCourse(@PathVariable("courseId") Long courseId) {
        return new ResponseEntity<>(playAdService.getPlayAdsByGolfCourse(courseId), HttpStatus.OK);
    }

    @GetMapping("/course/{golfClubId}")
    public ResponseEntity<List<PlayAdResponseDto>> getPlayAdsByGolfClub(@PathVariable("golfClubId") Long golfClubId) {
        return new ResponseEntity<>(playAdService.getPlayAdsByGolfClub(golfClubId), HttpStatus.OK);
    }

   /* @GetMapping("/course/{courseId}")
    public ResponseEntity<List<PlayAdResponseDto>> getPlayAdsByLocation(@PathVariable("courseId") Long courseId) {
        return new ResponseEntity<>(playAdService.getPlayAdsByGolfClub(golfClubId), HttpStatus.OK);
    }
*/
    @GetMapping("/suggestions/location/{userId}")
    public ResponseEntity<List<PlayAdResponseDto>> getSuggestedPlayAdsByUserLocation(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(playAdService.getSuggestedPlayAdsByUserLocation(userId), HttpStatus.OK);
    }


    @GetMapping("/suggestions/handicap/{userId}")
    public ResponseEntity<List<PlayAdResponseDto>> getSuggestedPlayAdsByHandicapRange(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(playAdService.getSuggestedPlayAdsByUserHandicapRange(userId), HttpStatus.OK);
    }


    @GetMapping("/booked/{userId}")
    public ResponseEntity<List<PlayAdResponseDto>> getBookedUsersPlayAds(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(playAdService.getBookedUsersPlayAds(userId), HttpStatus.OK);
    }

    @GetMapping("/created/{userId}")
    public ResponseEntity<List<PlayAdResponseDto>> getCreatedPlayAdsByUser(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(playAdService.getCreatedPlayAdsByUser(userId), HttpStatus.OK);
    }


}