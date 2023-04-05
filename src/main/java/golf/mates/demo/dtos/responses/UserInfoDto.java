package golf.mates.demo.dtos.responses;


import java.time.LocalDateTime;

public record UserInfoDto(long id, String email, String username, String golfClub, String location, double handicap, LocalDateTime createdAt) {

}
