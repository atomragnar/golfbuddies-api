package golf.mates.demo.dtos.responses;


import lombok.Data;

@Data
public class BookedPlayersDto {

    private long id;
    private long userId;
    private String username;
    private double handicap;

}
