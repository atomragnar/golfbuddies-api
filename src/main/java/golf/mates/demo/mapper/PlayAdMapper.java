package golf.mates.demo.mapper;


import golf.mates.demo.dtos.request.PlayAdRequestDto;
import golf.mates.demo.dtos.responses.AdReqResponseDto;
import golf.mates.demo.dtos.responses.PlayAdResponseDto;
import golf.mates.demo.entities.PlayAd;
import golf.mates.demo.entities.PlayAdRequest;
import golf.mates.demo.entities.PlayAdSlot;
import golf.mates.demo.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PlayAdMapper {

    public PlayAdMapper() { }


    public static PlayAdResponseDto toResponseDto(PlayAd playAd) {
        PlayAdResponseDto responseDto = new PlayAdResponseDto();
        responseDto.setPlayAdId(playAd.getId());
        responseDto.setCreatorId(playAd.getCreator().getId());
        responseDto.setCourseId(playAd.getCourseId());
        responseDto.setGolfClubId(playAd.getGolfClubId());
        responseDto.setLocationId(playAd.getLocationId());
        responseDto.setCreatorUsername(playAd.getCreator().getUsername());
        responseDto.setCourse(playAd.getCourse().getCourse());
        responseDto.setGolfClub(playAd.getGolfClub().getClub());
        responseDto.setLocation(playAd.getLocation().getDistrict());
        responseDto.setBookedPlayers(
                bookedPlayersArray(playAd.getSlots())
        );
        return responseDto;
    }

    public static List<PlayAdResponseDto> toResponseDtoList(List<PlayAd> playAdList) {
        return playAdList.stream().map(PlayAdMapper::toResponseDto).collect(Collectors.toList());
    }


    private static String[] bookedPlayersArray(List<PlayAdSlot> playAdSlotsList) {
        int length = playAdSlotsList.size();
        Optional<User> slot;
        String[] bookedPlayers = new String[length - 1];
        for (int i = 0; i < length; i++) {
            slot = Optional.of(playAdSlotsList.get(i).getPlayer());
            bookedPlayers[i] = slot.map(User::getUsername).orElse("empty");
        }
        return bookedPlayers;
    }


    public static PlayAd requestToEntity(PlayAdRequestDto requestDto, User user) {
        PlayAd playAd = new PlayAd(user);
        playAd.setCourseId(requestDto.getCourseId());
        playAd.setGolfClubId(requestDto.getGolfClubId());
        playAd.setLocationId(requestDto.getLocationId());
        return playAd;
    }


    public static AdReqResponseDto playAdRequestToRequestResponseDto(PlayAdRequest playAdRequest) {
        AdReqResponseDto adReqResponseDto = new AdReqResponseDto();
        adReqResponseDto.setPlayAdRequestId(playAdRequest.getId());
        adReqResponseDto.setPlayAdId(playAdRequest.getPlayAd().getId());
        adReqResponseDto.setRequesterId(playAdRequest.getRequester().getId());
        adReqResponseDto.setRequester(playAdRequest.getRequester().getUsername());
        adReqResponseDto.setRequesterHandicap(playAdRequest.getRequester().getHandicap());
        adReqResponseDto.setRequestCreatedTime(playAdRequest.getCreatedAt());
        return adReqResponseDto;
    }

    public static List<AdReqResponseDto> playAdRequestToRequestResponseDtoList(List<PlayAdRequest> playAdRequestList) {
        return playAdRequestList.stream().map(PlayAdMapper::playAdRequestToRequestResponseDto)
                .collect(Collectors.toList());
    }





}
