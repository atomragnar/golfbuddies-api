package golf.mates.demo.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import golf.mates.demo.dtos.request.UserRegistrationDto;
import golf.mates.demo.dtos.responses.UserInfoDto;
import golf.mates.demo.entities.Location;
import golf.mates.demo.entities.User;
import golf.mates.demo.mapper.UserMapper;
import golf.mates.demo.repositories.LocationRepository;
import golf.mates.demo.repositories.UserRepository;
import golf.mates.demo.services.UserService;

public class UserServiceTest {

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        openMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        // Arrange
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setUsername("user1");
        User user2 = new User();
        user2.setUsername("user2");
        users.add(user1);
        users.add(user2);
        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<UserInfoDto> result = userService.getAllUsers();

        // Assert
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("user1", result.get(0).username());
        Assertions.assertEquals("user2", result.get(1).username());
    }

    @Test
    public void testRegisterNewUser() {
        // Arrange
        UserRegistrationDto userDto = new UserRegistrationDto();
        userDto.setUsername("newuser");
        userDto.setPassword("password");
        Location location = new Location();
        location.setId(1L);
        userDto.setLocationId(location.getId());
        User user = UserMapper.toEntity(userDto);
        user.setId(1L);
        when(passwordEncoder.encode(userDto.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        userService.registerNewUser(userDto);

        // Assert
        verify(userRepository).save(user);
    }

    @Test
    public void testFindUserById() {
        // Arrange
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        User result = userService.findUserById(1L);

        // Assert
        Assertions.assertEquals(user, result);
    }

    @Test
    public void testFindUserById_NotFound() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Act + Assert
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            userService.findUserById(1L);
        });
    }

    @Test
    public void testExistsByUsername() {
        // Arrange
        when(userRepository.existsByUsernameIgnoreCase("existinguser")).thenReturn(true);

        // Act
        boolean result = userService.existsByUsername("existinguser");

        // Assert
        Assertions.assertTrue(result);
    }
}


