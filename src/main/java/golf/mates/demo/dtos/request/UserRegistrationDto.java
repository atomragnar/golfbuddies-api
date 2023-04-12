package golf.mates.demo.dtos.request;


import golf.mates.demo.validation.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegistrationDto {

    @UniqueUsername
    private String username;
    @Email
    private String email;
    @NotBlank
    private String password;

}
