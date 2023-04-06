package golf.mates.demo.services;

import golf.mates.demo.dtos.responses.GolfClubResponseDto;
import golf.mates.demo.entities.GolfClub;
import golf.mates.demo.entities.GolfCourse;
import golf.mates.demo.mapper.DataMapper;
import golf.mates.demo.repositories.GolfClubRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class GolfClubService {

    private final GolfClubRepository golfClubRepository;


    public List<GolfClub> getAllGolfClubs() {
        return golfClubRepository.findAll();
    }


    public GolfClub findById(Long id) {
        return golfClubRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    private List<GolfClub> checkIfEmpty() {
        List<GolfClub> golfClubs = golfClubRepository.findAll();
        if (!golfClubs.isEmpty()) {
            return golfClubs;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    public List<GolfClubResponseDto> getAllClubsAsDto() {
        return DataMapper.golfClubListToResponseDtoList(checkIfEmpty());
    }

    public List<GolfClub> findGolClubByLocationId(Long locationId) {
        List<GolfClub> golfClubs = golfClubRepository.findByLocation_Id(locationId);
        if (!golfClubs.isEmpty()) {
            return golfClubs;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
