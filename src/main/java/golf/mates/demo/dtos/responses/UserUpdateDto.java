package golf.mates.demo.dtos.responses;



import lombok.Data;

@Data
public class UserUpdateDto {

    long id;
    String username;
    double handicap;
    String location;



//    private boolean accountExpired = false;
//
//    private boolean accountLocked = false;
//
//    private boolean credentialsExpired = false;
//    private boolean accountEnabled = true;

}
