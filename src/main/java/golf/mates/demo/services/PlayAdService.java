package golf.mates.demo.services;

import golf.mates.demo.dtos.request.PlayAdRequestDto;
import golf.mates.demo.dtos.request.PlayAdUserRequestDto;
import golf.mates.demo.dtos.responses.PlayAdResponseDto;
import golf.mates.demo.entities.*;
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

    private final LocationService locationService;

    private final GolfClubService golfClubService;

    private final GolfCourseService golfCourseService;

    public PlayAdService(PlayAdRepository playAdRepository, PlayAdSlotRepository playAdSlotRepository, UserService userService, LocationService locationService, GolfClubService golfClubService, GolfCourseService golfCourseService) {
        this.playAdRepository = playAdRepository;
        this.playAdSlotRepository = playAdSlotRepository;
        this.userService = userService;
        this.locationService = locationService;
        this.golfClubService = golfClubService;
        this.golfCourseService = golfCourseService;
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

    public void removePlayerFromPlayAd(PlayAdUserRequestDto playAdUserRequestDto) {
        User user = userService.findUserById(playAdUserRequestDto.getUserId());
        PlayAd playAd = getPlayAdById(playAdUserRequestDto.getPlayAdId());
        playAd.removePlayer(user.getId());
        playAdRepository.save(playAd);
    }


    public void removePlayerFromPlayAd(long playAdId, long userId) {
        User user = userService.findUserById(userId);
        PlayAd playAd = getPlayAdById(playAdId);
        playAd.removePlayer(userId);
        playAdRepository.save(playAd);
    }

    public void createNewPlayAd(PlayAdRequestDto playAdRequestDto) {
        User user = userService.findUserById(playAdRequestDto.getUserId());
        Location location = locationService.findById(playAdRequestDto.getLocationId());
        GolfClub golfClub = golfClubService.findById(playAdRequestDto.getGolfClubId());
        GolfCourse golfCourse = golfCourseService.findById(playAdRequestDto.getCourseId());

        PlayAd playAd = new PlayAd(user);
        playAd.setLocation(location);
        playAd.setGolfClub(golfClub);
        playAd.setCourse(golfCourse);
        playAd.setTeeTime(playAdRequestDto.getTeeTime());
        playAd.setHasCar(playAdRequestDto.isHasCar());

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
        List<PlayAd> playAdList = playAdRepository.findByHandicapAverageBetween(start, end);
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

    public void deletePlayAd(Long playAdId) {
        playAdRepository.deleteById(playAdId);
    }
}
