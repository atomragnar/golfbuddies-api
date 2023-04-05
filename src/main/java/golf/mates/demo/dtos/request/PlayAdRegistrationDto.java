package golf.mates.demo.dtos.request;


import golf.mates.demo.entities.GolfCourse;
import golf.mates.demo.entities.Location;
import golf.mates.demo.validation.UniqueUsername;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class PlayAdRegistrationDto {

    private Long golfclub;
    private Timestamp teeTime;

    private boolean hasCar;

    private double handicap;
    private Long locationId;

    private String username;

}
