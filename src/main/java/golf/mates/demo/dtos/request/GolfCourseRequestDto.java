package golf.mates.demo.dtos.request;

import lombok.Data;

@Data
public class GolfCourseRequestDto {

    String golfCourse;
    int numOfHoles;
    long golfClubId;
    long locationId;


}
