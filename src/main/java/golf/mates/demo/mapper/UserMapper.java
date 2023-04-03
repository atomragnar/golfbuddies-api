package golf.mates.demo.mapper;

import golf.mates.demo.dtos.request.UserRegistrationDto;
import golf.mates.demo.dtos.responses.UserInfoDto;
import golf.mates.demo.entities.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public static User toEntity(UserRegistrationDto dto) {
        return new User(dto);
    }

    public static List<User> toEntityList(List<UserRegistrationDto> registrationDtos) {
        return registrationDtos.stream()
                .map(UserMapper::toEntity)
                .collect(Collectors.toList());
    }

    public static UserInfoDto toInfoDto(User user) {
        return new UserInfoDto(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getLocation().getDistrict(),
                user.getHandicap()
        );
    }

    public static List<UserInfoDto> toInfoDtoList(List<User> users) {
        return users.stream()
                .map(UserMapper::toInfoDto)
                .collect(Collectors.toList());
    }

}
