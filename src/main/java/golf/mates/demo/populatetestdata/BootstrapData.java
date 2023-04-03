/*
package golf.mates.demo.bootstrapdata;

import golf.mates.demo.entities.GolfClub;
import golf.mates.demo.entities.Location;
import golf.mates.demo.entities.User;
import golf.mates.demo.repositories.GolfClubRepository;
import golf.mates.demo.repositories.LocationRepository;
import golf.mates.demo.repositories.UserRepository;

import golf.mates.demo.security.jwt.PasswordUtils;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final GolfClubRepository golfClubRepository;
    private final LocationRepository locationRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {

    }

*/
/*    private void loadData() throws IOException {
        File file = ResourceUtils.getFile("classpath:csvdata/golfklubbar_lista.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            Map<String ,String> golfClubs = new LinkedHashMap<>();
            Map<String , Location> districtToObject = new HashMap<>();

            //line = new String(br.readLine().getBytes(), StandardCharsets.UTF_8);

            Set<String> landskapsSet = new HashSet<>();
            while((line = br.readLine() ) != null) {
                System.out.println(line);
                String[] golfClubData = line.split(",");
                String landskap = golfClubData[1].replaceAll("GDF", "").trim();
                golfClubs.put(golfClubData[0], landskap);
                landskapsSet.add(landskap);
            }

            landskapsSet.forEach(s -> {
                Location location = new Location(s);
                districtToObject.put(s, location);
                locationRepository.save(location);
            }
            );

            golfClubs.forEach( (k, v) -> {

                golfClubRepository.save(new GolfClub(k, districtToObject.get(v)));

            });

        }
        loadUserData();
    }*//*






    private void loadUserData() {

        Location location1 = locationRepository.findById(2L).get();
        Location location2 = locationRepository.findById(15L).get();
        Location location3 = locationRepository.findById(8L).get();

        GolfClub golfClub1 = golfClubRepository.findById(10L).get();
        GolfClub golfClub2 = golfClubRepository.findById(250L).get();
        GolfClub golfClub3 = golfClubRepository.findById(100L).get();

        User user1 = new User("user1", encoder.encode(PasswordUtils.sha256("password")), location1, golfClub1);
        User user2 = new User("user2", encoder.encode(PasswordUtils.sha256("password")), location2, golfClub2);
        User user3 = new User("user3", encoder.encode(PasswordUtils.sha256("password")), location3, golfClub3);

        // User admin = new User("admin", encoder.encode("admin"), "ROLE_ADMIN");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        golfClubRepository.save(golfClub1);
        golfClubRepository.save(golfClub2);
        golfClubRepository.save(golfClub3);

    }



}
*/
