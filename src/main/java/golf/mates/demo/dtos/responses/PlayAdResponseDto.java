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
    private double creatorHandicap;
    private String golfClub;
    private String course;
    private String location;
    private String time;
    private String teeTimeDate;
    private String teeTime;
    private boolean hasCar;
    private double averageHandicap;
    private BookedPlayersDto[] bookedPlayers;


}
