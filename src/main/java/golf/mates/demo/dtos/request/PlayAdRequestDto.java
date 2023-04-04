package golf.mates.demo.dtos.request;

import lombok.Data;

@Data
public class PlayAdRequestDto {

    private Long userId;
    private Long courseId;
    private Long golfClubId;
    private Long locationId;

}
