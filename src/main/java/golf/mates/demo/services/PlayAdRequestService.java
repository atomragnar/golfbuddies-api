package golf.mates.demo.services;

import golf.mates.demo.dtos.request.RequestAdSlotDto;
import golf.mates.demo.dtos.responses.AdReqResponseDto;
import golf.mates.demo.entities.PlayAd;
import golf.mates.demo.entities.PlayAdRequest;
import golf.mates.demo.entities.User;
import golf.mates.demo.mapper.PlayAdMapper;
import golf.mates.demo.repositories.PlayAdRequestRepository;
import golf.mates.demo.utils.PlayAdRequestStatus;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
@Slf4j
public class PlayAdRequestService {
    private final PlayAdRequestRepository playAdRequestRepository;
    private final PlayAdService playAdService;
    private final UserService userService;

    public PlayAdRequestService(PlayAdRequestRepository playAdRequestRepository, PlayAdService playAdService, UserService userService) {
        this.playAdRequestRepository = playAdRequestRepository;
        this.playAdService = playAdService;
        this.userService = userService;
    }

    public void handleNewPlayAdRequest(RequestAdSlotDto requestAdSlotDto) {
       // hitta user, skapa playadrequest, skicka playadrequest till playadservice
       User user = userService.findUserById(requestAdSlotDto.getUserId());
       PlayAd playAd = playAdService.getPlayAdById(requestAdSlotDto.getPlayAdId());
       PlayAdRequest playAdRequest = new PlayAdRequest(user, playAd);
       playAdRequest.setCreatedTimeForRequest();
       playAdRequestRepository.save(playAdRequest);
   }

   public List<AdReqResponseDto> findAllRequestByAdCreator(Long userId) {
       List<PlayAdRequest> playAdRequestList = playAdRequestRepository.findByPlayAd_Creator_Id(userId);
       return checkIfListIsEmpty(playAdRequestList);
   }

   private List<AdReqResponseDto> checkIfListIsEmpty(List<PlayAdRequest> playAdRequestList) {
       if (!playAdRequestList.isEmpty()) {
           return PlayAdMapper.playAdRequestToRequestResponseDtoList(playAdRequestList);
       }
       throw new ResponseStatusException(HttpStatus.NOT_FOUND);

   }


   public PlayAdRequest findById(Long id) {
        return playAdRequestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
   }


    public void setPlayAdRequestDenied(Long playAdRequestId) {
        PlayAdRequest playAdRequest = findById(playAdRequestId);
        playAdRequest.setStatus(PlayAdRequestStatus.DENIED);
        playAdRequestRepository.save(playAdRequest);
    }

    public void setPlayAdRequestAccepted(Long playAdRequestId) {
        PlayAdRequest playAdRequest = findById(playAdRequestId);
        playAdRequest.setPlayAdRequestApproved();
        playAdRequestRepository.save(playAdRequest);

    }

    public List<AdReqResponseDto> findAllRequestByUser(Long userId) {
        List<PlayAdRequest> playAdRequestList =  playAdRequestRepository.findByRequester_Id(userId);
        return checkIfListIsEmpty(playAdRequestList);
    }

    public List<AdReqResponseDto> findAllRequestByPlayAd(Long playAdId) {
        List<PlayAdRequest> playAdRequestList = playAdRequestRepository.findByPlayAd_Id(playAdId);
        return checkIfListIsEmpty(playAdRequestList);
    }
}