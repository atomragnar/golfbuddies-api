package golf.mates.demo.services;


import golf.mates.demo.dtos.request.UserRegistrationDto;
import golf.mates.demo.dtos.responses.UserInfoDto;

import golf.mates.demo.entities.GenderEnum;
import golf.mates.demo.entities.User;
import golf.mates.demo.mapper.UserMapper;
import golf.mates.demo.repositories.LocationRepository;
import golf.mates.demo.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final LocationRepository locationRepository;
    private final UserRepository userRepository;


    public List<UserInfoDto> getAllUsers() {
        return UserMapper.toInfoDtoList(userRepository.findAll());
    }

    public void registerNewUser(UserRegistrationDto userRegistrationDto) {
        User user = UserMapper.toEntity(userRegistrationDto);
        userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsernameIgnoreCase(username);
    }

    public UserInfoDto getUserInfo(String username) {
        return UserMapper.toInfoDto(
                userRepository.findByUsernameIgnoreCase(username)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        );
    }

    private List<UserInfoDto> getInfoDtoListOrNotFound(List<User> users) {
        if (!users.isEmpty()) {
            return UserMapper.toInfoDtoList(users);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void setUserGender(Long userId, GenderEnum gender) {
        User user = findUserById(userId);
        user.setGender(gender);
    }


    public List<UserInfoDto> findUserByLocationId(Long locationId) {
        List<User> users = userRepository.findByLocation_IdOrderByUsernameAsc(locationId);
        // TODO add check that the request is a valid location
        return getInfoDtoListOrNotFound(users);
    }

    public List<UserInfoDto> findUserByGolfClubId(Long golfClubId) {
        List<User> users = userRepository.findByGolfClub_IdOrderByUsernameAsc(golfClubId);
        // TODO add check that the request is a valid golfClub
        return getInfoDtoListOrNotFound(users);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


    public UserInfoDto getUserInfoById(Long userId) {
        return UserMapper.toInfoDto(findUserById(userId));

    }
}


