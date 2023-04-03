package golf.mates.demo.services;


import golf.mates.demo.entities.GolfClub;
import golf.mates.demo.entities.GolfCourse;
import golf.mates.demo.entities.Location;
import golf.mates.demo.repositories.GolfClubRepository;
import golf.mates.demo.repositories.GolfCourseRepository;
import golf.mates.demo.repositories.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final GolfClubRepository golfClubRepository;
    private final LocationRepository locationRepository;
    private final GolfCourseRepository golfCourseRepository;

    public GolfClub findGolfClubById(Long id) {
        return golfClubRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Location findLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public List<GolfClub> getAllGolfClubs() {
        return golfClubRepository.findAll();
    }

    public GolfClub getGolfClubById(Long id) {
        return golfClubRepository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public GolfClub createGolfClub(GolfClub golfClub) {
        return golfClubRepository.save(golfClub);
    }

    //TODO skriva klart denna metoden
    public GolfClub updateGolfClub(GolfClub golfClub) {
        Optional<GolfClub> savedGolfClub = golfClubRepository.findById(golfClub.getId());
        if (savedGolfClub.isPresent()) {
            return null;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public void deleteGolfClub(Long id) {
        golfClubRepository.deleteById(id);
    }

    public Location getLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }


    //TODO skriva klart denna metoden
    public Location updateLocation(Location location) {
        return null;
    }

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

    public List<GolfCourse> getAllGolfCourses() {
        return (List<GolfCourse>) golfCourseRepository.findAll();
    }

    public GolfCourse getGolfCourse(Long id) {
        return golfCourseRepository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public GolfCourse createGolfCourse(GolfCourse golfCourse) {
        return golfCourseRepository.save(golfCourse);
    }

    //TODO skriva klart denna metoden
    public GolfCourse updateGolfCourse(GolfCourse golfCourse) {
        return null;
    }

    public void deleteGolfCourse(Long id) {
    }


    private static class Mapper {
        private Mapper(){}

    }



}


