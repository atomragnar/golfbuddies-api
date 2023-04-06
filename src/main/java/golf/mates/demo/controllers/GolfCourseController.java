package golf.mates.demo.controllers;

import golf.mates.demo.dtos.responses.GolfClubResponseDto;
import golf.mates.demo.dtos.responses.GolfCourseDto;
import golf.mates.demo.services.GolfCourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
@Slf4j
public class GolfCourseController {

    private final GolfCourseService golfCourseService;


    @GetMapping("/select/list")
    public ResponseEntity<List<GolfCourseDto>> getAllCoursesAsDto() {
        return new ResponseEntity<>(golfCourseService.getAllGolfCourses(), HttpStatus.OK);
    }

}