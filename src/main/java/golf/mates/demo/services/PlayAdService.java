package golf.mates.demo.services;

import golf.mates.demo.dtos.request.PlayAdRegistrationDto;
import golf.mates.demo.dtos.request.UserRegistrationDto;
import golf.mates.demo.entities.GolfCourse;
import golf.mates.demo.entities.PlayAd;
import golf.mates.demo.entities.User;
import golf.mates.demo.repositories.GolfClubRepository;
import golf.mates.demo.repositories.GolfCourseRepository;
import golf.mates.demo.repositories.PlayAdRepository;
import golf.mates.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PlayAdService {

    private final UserRepository userRepository;

    private final PlayAdRepository playAdRepository;
    private final GolfClubRepository golfClubRepository;


    public void registerNewAd(PlayAdRegistrationDto playAdRegistrationDto) {
        PlayAd playad = new PlayAd(playAdRegistrationDto);
        playad.setGolfClub(golfClubRepository.findById(playAdRegistrationDto.getGolfclub()).get());
        playad.setCreatedBy((User) userRepository.findByUsernameIgnoreCase(playAdRegistrationDto.getUsername()).get());
        System.out.println(userRepository.findByUsernameIgnoreCase(playAdRegistrationDto.getUsername()).get());
        Set<String> players = playad.getPlayers();
        players.add( playAdRegistrationDto.getUsername());
        playad.setPlayers(players);
        playAdRepository.save(playad);
    }

    public List<PlayAd> getAllAds() {
        return playAdRepository.findAll();
    }

    public Object updateAd(String username, Long id) {
        PlayAd playad = playAdRepository.findById(id).get();
        Set<String> players = playad.getPlayers();

        if(players.size()<4) {
            players.add(username);
            playad.setEmptySlots(playad.getEmptySlots()-1);
            playad.setPlayers(players);
        }
        return playAdRepository.save(playad);
    }
}

