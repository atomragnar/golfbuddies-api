package golf.mates.demo.security.jwt;

import golf.mates.demo.dtos.request.UserRegistrationDto;
import golf.mates.demo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class JwtAuthenticationController {

    private final JwtTokenService tokenService;
    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationController(JwtTokenService tokenService,
                                       UserService userService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponse> generateToken(
            @RequestBody JwtTokenRequest jwtTokenRequest) {

        var authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        jwtTokenRequest.username(),
                        jwtTokenRequest.password());

        var authentication =
                authenticationManager.authenticate(authenticationToken);

        var token = tokenService.generateToken(authentication);

        return ResponseEntity.ok(new JwtTokenResponse(token));
    }

    @PostMapping("signup")
    public ResponseEntity<HttpStatus> addNewUser(@Valid @RequestBody UserRegistrationDto userRegistrationDto) {
        userService.registerNewUser(userRegistrationDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("checkToken")
    public ResponseEntity<HttpStatus> checkIfValidToken() {
        return new ResponseEntity<>(HttpStatus.OK);
    }


}