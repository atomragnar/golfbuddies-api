package golf.mates.demo.services;

import golf.mates.demo.entities.PlayAd;
import golf.mates.demo.repositories.PlayAdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PlayAdService {


    private PlayAdRepository playAdRepository;



    public PlayAd getPlayAdById(Long id) {
        return playAdRepository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


}
