package golf.mates.demo.controllers;

import golf.mates.demo.dtos.request.UserRegistrationDto;
import golf.mates.demo.dtos.responses.UserInfoDto;
import golf.mates.demo.dtos.responses.UserUpdateDto;
import golf.mates.demo.entities.GolfClub;
import golf.mates.demo.entities.GolfCourse;
import golf.mates.demo.entities.Location;
import golf.mates.demo.entities.User;
import golf.mates.demo.services.AdminService;
import golf.mates.demo.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final UserService userService;
    private final AdminService adminService;

    @GetMapping("/user/list")
    public ResponseEntity<List<UserInfoDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/user/create")
    public ResponseEntity<HttpStatus> addNewUser(@Valid @RequestBody UserRegistrationDto userRegistrationDto) {
        userService.registerNewUser(userRegistrationDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }


    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/user/update")
    public ResponseEntity<Object> updateUserInfo(@RequestBody UserUpdateDto userUpdateDto) {
        System.out.println(userUpdateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/golfclub")
    public ResponseEntity<Object> getAllGolfClubs() {
        return new ResponseEntity<>(adminService.getAllGolfClubs(), HttpStatus.OK);
    }

    @GetMapping("/golfclub/{id}")
    public ResponseEntity<GolfClub> getGolfClubById(@PathVariable Long id) {
        return new ResponseEntity<>(adminService.getGolfClubById(id), HttpStatus.OK);
    }

    @PostMapping("/golfclub")
    public ResponseEntity<GolfClub> createGolfClub(@RequestBody GolfClub golfClub) {
        return new ResponseEntity<>(adminService.createGolfClub(golfClub), HttpStatus.OK);
    }

    @PutMapping("/golfclub/{id}")
    public ResponseEntity<GolfClub> updateGolfClub(@PathVariable Long id, @RequestBody GolfClub golfClub) {
        return new ResponseEntity<>(adminService.updateGolfClub(golfClub), HttpStatus.OK);
    }


    @DeleteMapping("/golfclub/{id}")
    public ResponseEntity<Void> deleteGolfClub(@PathVariable Long id) {
        adminService.deleteGolfClub(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/location")
    public List<Location> getAllLocations() {
        return adminService.getAllLocations();
    }

    @GetMapping("/location/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        return new ResponseEntity<>(adminService.getLocationById(id), HttpStatus.OK);
    }

    @PostMapping("/location")
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        return new ResponseEntity<>(adminService.createLocation(location), HttpStatus.OK);
    }

    @PutMapping("/location/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Location location) {
        return new ResponseEntity<>(adminService.updateLocation(location), HttpStatus.OK);
    }


    @DeleteMapping("/location/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        adminService.deleteLocation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/golfcourse")
    public List<GolfCourse> getAllGolfCourses() {
        return adminService.getAllGolfCourses();
    }

    @GetMapping("/golfcourse/{id}")
    public ResponseEntity<GolfCourse> getGolfCourseById(@PathVariable Long id) {
        return new ResponseEntity<>(adminService.getGolfCourse(id), HttpStatus.OK);
    }

    @PostMapping("/golfcourse")
    public ResponseEntity<GolfCourse> createGolfCourse(@RequestBody GolfCourse golfCourse) {
        return new ResponseEntity<>(adminService.createGolfCourse(golfCourse), HttpStatus.OK);
    }

    @PutMapping("/golfcourse/{id}")
    public ResponseEntity<GolfCourse> updateGolfCourse(@PathVariable Long id, @RequestBody GolfCourse golfCourse) {
        return new ResponseEntity<>(adminService.updateGolfCourse(golfCourse), HttpStatus.OK);
    }


    @DeleteMapping("/golfcourse/{id}")
    public ResponseEntity<Void> deleteGolfCourse(@PathVariable Long id) {
        adminService.deleteGolfCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }







}