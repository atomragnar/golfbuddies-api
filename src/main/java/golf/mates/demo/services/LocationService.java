package golf.mates.demo.services;

import golf.mates.demo.entities.Location;
import golf.mates.demo.repositories.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;


    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }



}
