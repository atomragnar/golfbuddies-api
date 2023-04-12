package golf.mates.demo.dtos.request;


import lombok.Data;

@Data
public class UserUpdateInfoDto {

    private Long userId;
    private Long locationId;
    private Long golfClubId;
    private double handicap;


}
