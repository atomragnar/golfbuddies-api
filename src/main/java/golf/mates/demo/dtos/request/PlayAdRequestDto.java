package golf.mates.demo.dtos.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PlayAdRequestDto {

    private Long userId;
    private Long courseId;
    private Long golfClubId;
    private Long locationId;
    private LocalDateTime teeTime;
    private boolean hasCar;

}
