package golf.mates.demo.controllers;

import golf.mates.demo.dtos.responses.UserInfoDto;
import golf.mates.demo.services.UserService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserApiController {


    private final UserService userService;


    @GetMapping("/golfclub/{golfClubId}")
    public ResponseEntity<List<UserInfoDto>> findAllUsersAtGolfClubById(@PathVariable("golfClubId") @Positive Long golfClubId) {
        return new ResponseEntity<>(userService.findUserByLocationId(golfClubId), HttpStatus.OK);
    }

    @GetMapping("/location/{locationId}")
    public ResponseEntity<List<UserInfoDto>> findAllUsersAtLocationById(@PathVariable("locationId") @Positive Long locationId) {
        return new ResponseEntity<>(userService.findUserByLocationId(locationId), HttpStatus.OK);
    }




}