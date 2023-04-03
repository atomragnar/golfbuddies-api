package golf.mates.demo.dtos.request;


import golf.mates.demo.validation.UniqueUsername;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegistrationDto {

    @UniqueUsername
    private String username;
    @NotBlank
    private String password;
    private double handicap;
    private long locationId;


}
