package golf.mates.demo.services;

import golf.mates.demo.dtos.request.PlayAdRequestDto;
import golf.mates.demo.dtos.responses.PlayAdResponseDto;
import golf.mates.demo.entities.PlayAd;
import golf.mates.demo.entities.User;
import golf.mates.demo.mapper.PlayAdMapper;
import golf.mates.demo.repositories.PlayAdRepository;
import golf.mates.demo.repositories.PlayAdSlotRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PlayAdService {

    private final PlayAdRepository playAdRepository;
    private final PlayAdSlotRepository playAdSlotRepository;
    private final UserService userService;

    public PlayAdService(PlayAdRepository playAdRepository, PlayAdSlotRepository playAdSlotRepository, UserService userService) {
        this.playAdRepository = playAdRepository;
        this.playAdSlotRepository = playAdSlotRepository;
        this.userService = userService;
    }

    public PlayAdResponseDto getPlayAdResponseDtoById(Long id) {
        PlayAd playAd = getPlayAdById(id);
        return PlayAdMapper.toResponseDto(playAd);
    }

    public PlayAd getPlayAdById(Long id) {
        return playAdRepository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void addPlayerToPlayAd(Long playAdId, Long userId) {
        User user = userService.findUserById(userId);
        PlayAd playAd = getPlayAdById(playAdId);


    }

    public List<PlayAdResponseDto> getAllPlayAdsByUserId(Long userId) {
        List<PlayAd> playAdList = playAdRepository.findByCreator_Id(userId);
        return checkIfListIsEmpty(playAdList);
    }

    public List<PlayAdResponseDto> getAllPlayAds() {
        List<PlayAd> playAdList = playAdRepository.findAllBy();
        return checkIfListIsEmpty(playAdList);
    }

    private List<PlayAdResponseDto> checkIfListIsEmpty(List<PlayAd> playAdList) {
        if (!playAdList.isEmpty()) {
            return PlayAdMapper.toResponseDtoList(playAdList);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }


    public PlayAdResponseDto createPlayAd(User user) {
        PlayAd playAd = new PlayAd(user);
        playAdRepository.save(playAd);
        return PlayAdMapper.toResponseDto(playAd);
    }

    public void removePlayerFromPlayAd(long playAdId, String username) {
        if (userService.existsByUsername(username)) {
            PlayAd playAd = getPlayAdById(playAdId);
            playAd.removePlayer(username);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void createNewPlayAd(PlayAdRequestDto playAdRequestDto) {
        User user = userService.findUserById(playAdRequestDto.getUserId());
        PlayAd playAd = PlayAdMapper.requestToEntity(playAdRequestDto, user);
        playAdRepository.save(playAd);

    }

    public List<PlayAdResponseDto> getPlayAdsByGolfCourse(Long courseId) {
        List<PlayAd> playAdList = playAdRepository.findByCourse_Id(courseId);
        return PlayAdMapper.toResponseDtoList(playAdList);
    }

    public List<PlayAdResponseDto> getPlayAdsByGolfClub(Long golfClubId) {
        List<PlayAd> playAdList = playAdRepository.findByGolfClub_Id(golfClubId);
        return PlayAdMapper.toResponseDtoList(playAdList);
    }

    public List<PlayAdResponseDto> getSuggestedPlayAdsByUserLocation(Long userId) {
        User user = userService.findUserById(userId);
        List<PlayAd> playAdList = playAdRepository.findByLocation_Id(user.getLocation().getId());
        return PlayAdMapper.toResponseDtoList(playAdList);
    }

    public List<PlayAdResponseDto> getSuggestedPlayAdsByUserHandicapRange(Long userId) {
        User user = userService.findUserById(userId);
        double end = user.getHandicap() + 8;
        double start = user.getHandicap() - 8;
        List<PlayAd> playAdList = playAdRepository.findByCreator_HandicapBetween(start, end);
        return PlayAdMapper.toResponseDtoList(playAdList);
    }

    public List<PlayAdResponseDto> getBookedUsersPlayAds(Long userId) {
        List<PlayAd> playAdList = playAdRepository.findBySlots_Player_Id(userId);
        return PlayAdMapper.toResponseDtoList(playAdList);

    }

    public List<PlayAdResponseDto> getCreatedPlayAdsByUser(Long userId) {
        List<PlayAd> playAdList = playAdRepository.findByCreator_Id(userId);
        return PlayAdMapper.toResponseDtoList(playAdList);
    }
}
