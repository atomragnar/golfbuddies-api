package golf.mates.demo.mapper;


import golf.mates.demo.dtos.request.PlayAdRequestDto;
import golf.mates.demo.dtos.responses.AdReqResponseDto;
import golf.mates.demo.dtos.responses.BookedPlayersDto;
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
        responseDto.setCourseId(playAd.getCourse().getId());
        responseDto.setGolfClubId(playAd.getGolfClub().getId());
        responseDto.setLocationId(playAd.getLocation().getId());
        responseDto.setCreatorUsername(playAd.getCreator().getUsername());
        responseDto.setCourse(playAd.getCourse().getCourse());
        responseDto.setGolfClub(playAd.getGolfClub().getClub());
        responseDto.setLocation(playAd.getLocation().getDistrict());
        responseDto.setTime(playAd.getCreatedAt().toString().substring(0, 10));
        responseDto.setBookedPlayers(
                bookedPlayersArray(playAd.getSlots())
        );
        return responseDto;
    }

    public static List<PlayAdResponseDto> toResponseDtoList(List<PlayAd> playAdList) {
        return playAdList.stream().map(PlayAdMapper::toResponseDto).collect(Collectors.toList());
    }


    private static BookedPlayersDto[] bookedPlayersArray(List<PlayAdSlot> playAdSlotsList) {
        int length = playAdSlotsList.size();
        BookedPlayersDto[] bookedPlayers = new BookedPlayersDto[length];
        for (int i = 0; i < length; i++) {
            User player = playAdSlotsList.get(i).getPlayer();
            if (player != null) {
                BookedPlayersDto bookedPlayersDto = new BookedPlayersDto();
                bookedPlayersDto.setUsername(player.getUsername());
                bookedPlayersDto.setUserId(player.getId());
                bookedPlayersDto.setId(i);
                bookedPlayers[i] = bookedPlayersDto;
            } else {
                BookedPlayersDto bookedPlayersDto = new BookedPlayersDto();
                bookedPlayersDto.setUsername("empty");
                bookedPlayersDto.setUserId(0);
                bookedPlayersDto.setId(i);
                bookedPlayers[i] = bookedPlayersDto;
            }
        }
        return bookedPlayers;
    }


    /*public static PlayAd requestToEntity(PlayAdRequestDto requestDto, User user) {
        PlayAd playAd = new PlayAd(user);
        playAd.setCourseId(requestDto.getCourseId());
        playAd.setGolfClubId(requestDto.getGolfClubId());
        playAd.setLocationId(requestDto.getLocationId());
        return playAd;
    }
*/

    public static AdReqResponseDto playAdRequestToRequestResponseDto(PlayAdRequest playAdRequest) {
        AdReqResponseDto adReqResponseDto = new AdReqResponseDto();
        adReqResponseDto.setPlayAdRequestId(playAdRequest.getId());
        adReqResponseDto.setPlayAdId(playAdRequest.getPlayAd().getId());
        adReqResponseDto.setRequesterId(playAdRequest.getRequester().getId());
        adReqResponseDto.setRequester(playAdRequest.getRequester().getUsername());
        adReqResponseDto.setRequesterHandicap(playAdRequest.getRequester().getHandicap());
        adReqResponseDto.setRequestCreatedTime(playAdRequest.getCreatedAt().toString().substring(0, 10));
        adReqResponseDto.setStatus(playAdRequest.getStatus().toString());
        return adReqResponseDto;
    }

    public static List<AdReqResponseDto> playAdRequestToRequestResponseDtoList(List<PlayAdRequest> playAdRequestList) {
        return playAdRequestList.stream().map(PlayAdMapper::playAdRequestToRequestResponseDto)
                .collect(Collectors.toList());
    }





}
