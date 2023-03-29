package golf.mates.demo.dtos.responses;

import golf.mates.demo.entities.User;

public record UserInfoDto(String Id, String Username, String location, double handicap) {

}
