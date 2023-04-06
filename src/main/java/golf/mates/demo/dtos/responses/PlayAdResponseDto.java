package golf.mates.demo.dtos.responses;


import lombok.Data;

@Data
public class PlayAdResponseDto {

    private long playAdId;
    private long creatorId;
    private long golfClubId;
    private long courseId;
    private long locationId;
    private String creatorUsername;
    private String golfClub;
    private String course;
    private String location;
    private String time;
    private BookedPlayersDto[] bookedPlayers;


}
