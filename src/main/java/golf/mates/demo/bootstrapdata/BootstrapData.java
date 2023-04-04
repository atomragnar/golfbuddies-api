package golf.mates.demo.bootstrapdata;

import golf.mates.demo.dtos.request.PlayAdRegistrationDto;
import golf.mates.demo.entities.GolfClub;
import golf.mates.demo.entities.Location;
import golf.mates.demo.entities.PlayAd;
import golf.mates.demo.entities.User;
import golf.mates.demo.repositories.GolfClubRepository;
import golf.mates.demo.repositories.LocationRepository;
import golf.mates.demo.repositories.PlayAdRepository;
import golf.mates.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.*;


@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final GolfClubRepository golfClubRepository;
    private final LocationRepository locationRepository;
    private final PlayAdRepository playAdRepository;

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() throws IOException {
        File file = ResourceUtils.getFile("classpath:csvdata/golfklubbar_lista.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            Map<String ,String> golfClubs = new LinkedHashMap<>();
            Map<String , Location> districtToObject = new HashMap<>();

            Set<String> landskapsSet = new HashSet<>();
            while((line = br.readLine()) != null) {
                String[] golfClubData = line.split(";");
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



    }

    private void loadUserData() {

        Location location1 = locationRepository.findById(2L).get();
        Location location2 = locationRepository.findById(15L).get();
        Location location3 = locationRepository.findById(8L).get();

        GolfClub golfClub1 = golfClubRepository.findById(10L).get();
        GolfClub golfClub2 = golfClubRepository.findById(250L).get();
        GolfClub golfClub3 = golfClubRepository.findById(100L).get();

        PlayAd playAd1 = new PlayAd(new PlayAdRegistrationDto());
        playAd1.setGolfClub(golfClubRepository.findById(1L).get());
        PlayAd playAd2 = new PlayAd(new PlayAdRegistrationDto());
        playAd2.setGolfClub(golfClubRepository.findById(3L).get());
        PlayAd playAd3 = new PlayAd(new PlayAdRegistrationDto());
        playAd3.setGolfClub(golfClubRepository.findById(4L).get());


        User user1 = new User("user1", encoder.encode("password"), location1, golfClub1, 12);
        User user2 = new User("user2", encoder.encode("password"), location2, golfClub2, 3);
        User user3 = new User("user3", encoder.encode("password"), location3, golfClub3,4);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        golfClubRepository.save(golfClub1);
        golfClubRepository.save(golfClub2);
        golfClubRepository.save(golfClub3);

        playAdRepository.save(playAd1);
        playAdRepository.save(playAd2);
        playAdRepository.save(playAd3);

    }



}
