package golf.mates.demo.services;

import golf.mates.demo.dtos.request.PlayAdRequestDto;
import golf.mates.demo.entities.PlayAd;
import golf.mates.demo.entities.User;
import golf.mates.demo.mapper.PlayAdMapper;
import golf.mates.demo.repositories.PlayAdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PlayAdService {


    private final PlayAdRepository playAdRepository;
    private final UserService userService;

    public PlayAdService(PlayAdRepository playAdRepository, UserService userService) {
        this.playAdRepository = playAdRepository;
        this.userService = userService;
    }


    public PlayAd getPlayAdById(Long id) {
        return playAdRepository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void addPlayerToPlayAd(Long playAdId, Long userId) {
        User user = userService.findUserById(userId);
        PlayAd playAd = getPlayAdById(playAdId);


    }

    public List<PlayAd> getAllPlayAdsByUserId(Long userId) {
        List<PlayAd> playAdList = playAdRepository.findByCreator_Id(userId);
        return checkIfListIsEmpty(playAdList);
    }

    public List<PlayAd> getAllPlayAds() {
        List<PlayAd> playAdList = playAdRepository.findAllBy();
        return checkIfListIsEmpty(playAdList);
    }

    private List<PlayAd> checkIfListIsEmpty(List<PlayAd> playAdList) {
        if (!playAdList.isEmpty()) {
            return playAdList;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }


    public PlayAd createPlayAd(User user) {
        return null;
    }

    public void removePlayerFromPlayAd(long playAdId, String username) {
        if (userService.existsByUsername(username)) {
            PlayAd playAd = getPlayAdById(playAdId);
            playAd.removePlayer(username);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }


    public PlayAd createNewPlayAd(PlayAdRequestDto playAdRequestDto) {
        User user = userService.findUserById(playAdRequestDto.getUserId());
        PlayAd playAd = PlayAdMapper.requestToEntity(playAdRequestDto, user);
        return playAdRepository.save(playAd);

    }
}
