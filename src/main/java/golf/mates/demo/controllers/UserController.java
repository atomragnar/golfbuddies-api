package golf.mates.demo.controllers;

import golf.mates.demo.dtos.request.UserRegistrationDto;
import golf.mates.demo.dtos.responses.UserInfoDto;
import golf.mates.demo.entities.GenderEnum;
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

    @GetMapping("{username}/info")
    public ResponseEntity<Object> getUserInfo(@PathVariable("username") @NotBlank String username) {
        return new ResponseEntity<>(userService.getUserInfo(username), HttpStatus.OK);
    }

    @GetMapping("/info/id/{userId}")
    public ResponseEntity<Object> getUserInfoByUserID(@PathVariable("userId") @NotBlank Long userId) {
        return new ResponseEntity<>(userService.getUserInfoById(userId), HttpStatus.OK);
    }

    @PutMapping("update/{username}/info")
    public ResponseEntity<Object> updateUserInfo(@RequestBody UserInfoDto userInfoDto, @PathVariable String username) {
        return null;
    }

    @PutMapping("gender/male/{userId}")
    public ResponseEntity<Object> updateUserGenderMale(@PathVariable("userId") Long userId) {
        userService.setUserGender(userId, GenderEnum.MAN);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("gender/female/{userId}")
    public ResponseEntity<Object> updateUserGenderFemale(@PathVariable("userId") Long userId) {
        userService.setUserGender(userId, GenderEnum.KVINNA);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("gender/nonbinary/{userId}")
    public ResponseEntity<Object> updateUserGenderNonBinary(@PathVariable("userId") Long userId) {
        userService.setUserGender(userId, GenderEnum.ICKEBINÄR);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("gender/unknown/{userId}")
    public ResponseEntity<Object> updateUserGenderUnknown(@PathVariable("userId") Long userId) {
        userService.setUserGender(userId, GenderEnum.OKÄNT);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
