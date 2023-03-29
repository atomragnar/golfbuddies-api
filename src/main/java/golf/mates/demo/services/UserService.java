package golf.mates.demo.services;


import golf.mates.demo.dtos.request.UserRegistrationDto;
import golf.mates.demo.dtos.responses.UserInfoDto;
import golf.mates.demo.entities.User;
import golf.mates.demo.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public List<UserInfoDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(Mapper::UserToUserInfoDto)
                .collect(Collectors.toList());
    }

    public void registerNewUser(UserRegistrationDto userRegistrationDto) {
        User user = Mapper.userRegistrationDtoToUser(userRegistrationDto, encoder);
        userRepository.save(user);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsernameIgnoreCase(username);
    }

    public UserInfoDto getUserInfo(String username) {
        return Mapper.UserToUserInfoDto(
                userRepository.findByUsernameIgnoreCase(username)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        );
    }

    public List<UserInfoDto> findUserByLocationId(Long locationId) {
        List<User> users = userRepository.findByLocation_IdOrderByUsernameAsc(locationId);

        // TODO add check that the request is a valid location

        if (!users.isEmpty()) {
            return users.stream()
                    .map(Mapper::UserToUserInfoDto)
                    .collect(Collectors.toList());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    public List<UserInfoDto> findUserByGolfClubId(Long golfClubId) {
        List<User> users = userRepository.findByGolfClub_IdOrderByUsernameAsc(golfClubId);

        // TODO add check that the request is a valid golfClub

        if (!users.isEmpty()) {
            return users.stream()
                    .map(Mapper::UserToUserInfoDto)
                    .collect(Collectors.toList());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }





    private static class Mapper {
        private Mapper(){}

        private static User userRegistrationDtoToUser(UserRegistrationDto userRegistrationDto, PasswordEncoder encoder) {
            User user = new User(userRegistrationDto);
            user.setPassword(encoder.encode(user.getPassword()));
            return user;
        }

        private static UserInfoDto UserToUserInfoDto(User user) {
            return new UserInfoDto(
                user.getId().toString(),
                    user.getUsername(),
                    user.getLocation().getDistrict(),
                    user.getHandicap()
            );
        }

    }







}
