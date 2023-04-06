package golf.mates.demo.services;

import golf.mates.demo.dtos.responses.GolfClubResponseDto;
import golf.mates.demo.dtos.responses.LocationDto;
import golf.mates.demo.entities.GolfClub;
import golf.mates.demo.entities.Location;
import golf.mates.demo.mapper.DataMapper;
import golf.mates.demo.repositories.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }


    public Location findById(Long locationId) {
        return locationRepository.findById(locationId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }


    private List<Location> checkIfEmpty() {
        List<Location> locationList = locationRepository.findAll();
        if (!locationList.isEmpty()) {
            return locationList;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    public List<LocationDto> getAllLocationsAsDto() {
        return DataMapper.locationListToDtoList(checkIfEmpty());
    }


}
