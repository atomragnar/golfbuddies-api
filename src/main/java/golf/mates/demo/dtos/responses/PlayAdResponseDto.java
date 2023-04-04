package golf.mates.demo.dtos.responses;


import lombok.Data;

@Data
public class PlayAdResponseDto {

    private long playAdId;
    private long creatorId;
    private String creatorUsername;
    private String[] bookedPlayers;



}
