package golf.mates.demo.controllers;

import golf.mates.demo.dtos.request.UserRegistrationDto;
import golf.mates.demo.dtos.responses.UserInfoDto;
import golf.mates.demo.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("list")
    public ResponseEntity<List<UserInfoDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<HttpStatus> addNewUser(@Valid @RequestBody UserRegistrationDto userRegistrationDto) {
        userService.registerNewUser(userRegistrationDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{username}/info")
    public ResponseEntity<Object> getUserInfo(@PathVariable("username") @NotBlank String username) {
        return new ResponseEntity<>(userService.getUserInfo(username), HttpStatus.OK);
    }

    @PutMapping("update/{username}/info")
    public ResponseEntity<Object> updateUserInfo(@RequestBody UserInfoDto userInfoDto, @PathVariable String username) {
        return null;
    }


}
