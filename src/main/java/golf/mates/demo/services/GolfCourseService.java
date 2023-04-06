package golf.mates.demo.services;


import golf.mates.demo.dtos.responses.GolfCourseDto;
import golf.mates.demo.entities.GolfCourse;
import golf.mates.demo.entities.Location;
import golf.mates.demo.mapper.DataMapper;
import golf.mates.demo.repositories.GolfCourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class GolfCourseService {

    private final GolfCourseRepository golfCourseRepository;

    public GolfCourseService(GolfCourseRepository golfCourseRepository) {
        this.golfCourseRepository = golfCourseRepository;
    }

    public GolfCourse findById(Long id) {
        return golfCourseRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }


    public List<GolfCourseDto> getAllGolfCourses() {
        List<GolfCourse> golfCourses = (List<GolfCourse>) golfCourseRepository.findAll();
        if (!golfCourses.isEmpty()) {
            return DataMapper.golfCourseListToDtoList(golfCourses);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }


}
