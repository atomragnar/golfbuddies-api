package golf.mates.demo.populatetestdata;


import golf.mates.demo.entities.GolfClub;
import golf.mates.demo.entities.GolfCourse;
import golf.mates.demo.entities.Location;
import golf.mates.demo.entities.User;
import golf.mates.demo.repositories.GolfClubRepository;
import golf.mates.demo.repositories.GolfCourseRepository;
import golf.mates.demo.repositories.LocationRepository;
import golf.mates.demo.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TestDataPopulator {

    private final LocationRepository locationRepository;
    private final GolfClubRepository golfClubRepository;
    private final GolfCourseRepository golfCourseRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public TestDataPopulator(LocationRepository locationRepository, GolfClubRepository golfClubRepository, GolfCourseRepository golfCourseRepository, UserRepository userRepository, PasswordEncoder encoder) {
        this.locationRepository = locationRepository;
        this.golfClubRepository = golfClubRepository;
        this.golfCourseRepository = golfCourseRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @PostConstruct
    public void populateTestData() {
        // Create some test locations
        Location lothlorien = new Location("Lothlórien");
        Location gondor = new Location("Gondor");
        Location rohan = new Location("Rohan");
        Location shire = new Location("The Shire");
        Location rivendell = new Location("Rivendell");
        Location moria = new Location("Moria");
        Location isengard = new Location("Isengard");
        Location mordor = new Location("Mordor");
        Location erebor = new Location("Erebor");
        Location minasTirith = new Location("Minas Tirith");

        locationRepository.saveAll(Arrays.asList(lothlorien, gondor, rohan, shire, rivendell, moria, isengard, mordor, erebor, minasTirith));

        // Create some test golf clubs
        GolfClub carasGaladhonGolfClub = new GolfClub("Caras Galadhon Golf Club", lothlorien);
        GolfClub minasTirithGolfClub = new GolfClub("Minas Tirith Golf Club", minasTirith);
        GolfClub theShireGolfClub = new GolfClub("The Shire Golf Club", shire);
        GolfClub ereborGolfClub = new GolfClub("Erebor Golf Club", erebor);
        GolfClub rivendellGolfClub = new GolfClub("Rivendell Golf Club", rivendell);

        golfClubRepository.saveAll(Arrays.asList(carasGaladhonGolfClub, minasTirithGolfClub, theShireGolfClub, ereborGolfClub, rivendellGolfClub));

        // Create some test golf clubs
        GolfClub fangornGolfClub = new GolfClub("Fangorn Golf Club", rohan);
        GolfClub helmsDeepGolfClub = new GolfClub("Helm's Deep Golf Club", rohan);
        GolfClub moriaGolfClub = new GolfClub("Moria Golf Club", moria);
        GolfClub isengardGolfClub = new GolfClub("Isengard Golf Club", isengard);
        GolfClub osgiliathGolfClub = new GolfClub("Osgiliath Golf Club", gondor);
        GolfClub shireGolfClub = new GolfClub("Shire Golf Club", shire);
        GolfClub mordorGolfClub = new GolfClub("Mordor Golf Club", mordor);

        golfClubRepository.saveAll(Arrays.asList(fangornGolfClub, helmsDeepGolfClub, rivendellGolfClub, moriaGolfClub, isengardGolfClub, osgiliathGolfClub, shireGolfClub, ereborGolfClub, mordorGolfClub, minasTirithGolfClub));

        // Create some test golf clubs

        GolfClub lindonGolfClub = new GolfClub("Lindon Golf Club", rivendell);
        GolfClub khazadDumGolfClub = new GolfClub("Khazad-dûm Golf Club", moria);
        GolfClub durinsFollyGolfClub = new GolfClub("Durin's Folly Golf Club", moria);
        GolfClub orthancGolfClub = new GolfClub("Orthanc Golf Club", isengard);
        GolfClub baradDurGolfClub = new GolfClub("Barad-dûr Golf Club", mordor);
        GolfClub cirithUngolGolfClub = new GolfClub("Cirith Ungol Golf Club", mordor);
        GolfClub minasAnorGolfClub = new GolfClub("Minas Anor Golf Club", gondor);
        GolfClub hobbitonGolfClub = new GolfClub("Hobbiton Golf Club", shire);
        GolfClub bucklandGolfClub = new GolfClub("Buckland Golf Club", shire);
        GolfClub lonelyMountainGolfClub = new GolfClub("Lonely Mountain Golf Club", erebor);
        GolfClub pelennorFieldsGolfClub = new GolfClub("Pelennor Fields Golf Club", minasTirith);

        golfClubRepository.saveAll(Arrays.asList(fangornGolfClub, helmsDeepGolfClub, rivendellGolfClub, lindonGolfClub, khazadDumGolfClub, durinsFollyGolfClub, orthancGolfClub, baradDurGolfClub, cirithUngolGolfClub, osgiliathGolfClub, minasAnorGolfClub, hobbitonGolfClub, bucklandGolfClub, ereborGolfClub, lonelyMountainGolfClub, minasTirithGolfClub, pelennorFieldsGolfClub));


        // Create some test golf courses
        GolfCourse course1 = new GolfCourse("Nenya Greens", 18, carasGaladhonGolfClub, lothlorien);
        GolfCourse course2 = new GolfCourse("Mallorn Course", 18, carasGaladhonGolfClub, lothlorien);
        GolfCourse course3 = new GolfCourse("Pelennor Links", 18, minasTirithGolfClub, minasTirith);
        GolfCourse course4 = new GolfCourse("The Osgiliath Club", 18, minasTirithGolfClub, gondor);
        GolfCourse course5 = new GolfCourse("Hobbit Hills", 18, theShireGolfClub, shire);
        GolfCourse course6 = new GolfCourse("Dale Greens", 18, ereborGolfClub, erebor);
        GolfCourse course7 = new GolfCourse("Elrond's Fairway", 18, rivendellGolfClub, rivendell);

        golfCourseRepository.saveAll(Arrays.asList(course1, course2, course3, course4, course5, course6, course7));

        // Create some test golf courses
        GolfCourse fangorn1 = new GolfCourse("Entwood Greens", 18, fangornGolfClub, rohan);
        GolfCourse fangorn2 = new GolfCourse("Treebeard's Nine", 9, fangornGolfClub, rohan);
        GolfCourse helmsDeep1 = new GolfCourse("Deeping Fairway", 18, helmsDeepGolfClub, rohan);
        GolfCourse helmsDeep2 = new GolfCourse("Rohirrim's Rest", 9, helmsDeepGolfClub, rohan);
        GolfCourse rivendell1 = new GolfCourse("Elrond's Embrace", 18, rivendellGolfClub, rivendell);
        GolfCourse rivendell2 = new GolfCourse("Gil-Galad's Nine", 9, rivendellGolfClub, rivendell);
        GolfCourse lindon1 = new GolfCourse("Beleriand Bunker", 18, lindonGolfClub, rivendell);
        GolfCourse lindon2 = new GolfCourse("Maedhros's Nine", 9, lindonGolfClub, rivendell);
        GolfCourse khazadDum1 = new GolfCourse("Balrog's Bane", 18, khazadDumGolfClub, moria);
        GolfCourse khazadDum2 = new GolfCourse("Mithril's Nine", 9, khazadDumGolfClub, moria);
        GolfCourse durinsFolly1 = new GolfCourse("Dwalin's Delight", 18, durinsFollyGolfClub, moria);
        GolfCourse durinsFolly2 = new GolfCourse("Gimli's Nine", 9, durinsFollyGolfClub, moria);
        GolfCourse orthanc1 = new GolfCourse("Saruman's Challenge", 18, orthancGolfClub, isengard);
        GolfCourse orthanc2 = new GolfCourse("Uruk-hai's Nine", 9, orthancGolfClub, isengard);
        GolfCourse baradDur1 = new GolfCourse("Eye of Sauron Links", 18, baradDurGolfClub, mordor);
        GolfCourse baradDur2 = new GolfCourse("Nazgul's Nine", 9, baradDurGolfClub, mordor);
        GolfCourse cirithUngol1 = new GolfCourse("Shelob's Lair", 18, cirithUngolGolfClub, mordor);
        GolfCourse cirithUngol2 = new GolfCourse("Mordor's Nine", 9, cirithUngolGolfClub, mordor);
        GolfCourse osgiliath1 = new GolfCourse("Gondor's Glory", 18, osgiliathGolfClub, gondor);
        GolfCourse osgiliath2 = new GolfCourse("Anduril's Nine", 9, osgiliathGolfClub, gondor);

        golfCourseRepository.saveAll(Arrays.asList(fangorn1, fangorn2, helmsDeep1, helmsDeep2, rivendell1, rivendell2, lindon1, lindon2, khazadDum1,
                khazadDum2, durinsFolly1, durinsFolly2, orthanc1, orthanc2, baradDur1, baradDur2, cirithUngol1, cirithUngol2, osgiliath1, osgiliath2));


        // Create some test users
        User frodo = new User("frodo@bagend.me", "Frodo_Baggins", encoder.encode("password"), shire, hobbitonGolfClub);
        frodo.setHandicap(Math.round(Math.random() * 30 * 10.0) / 10.0);

        User sam = new User("sam@bagend.me", "Samwise_Gamgee", encoder.encode("password"), shire, hobbitonGolfClub);
        sam.setHandicap(Math.round(Math.random() * 30 * 10.0) / 10.0);

        User gandalf = new User("gandalf@valinor.com", "Mithrandir", encoder.encode("password"), shire, lonelyMountainGolfClub);
        gandalf.setHandicap(Math.round(Math.random() * 30 * 10.0) / 10.0);

        User aragorn = new User("aragorn@gondor.gov", "Aragorn_Elessar", encoder.encode("password"), gondor, osgiliathGolfClub);
        aragorn.setHandicap(Math.round(Math.random() * 30 * 10.0) / 10.0);

        User legolas = new User("legolas@mirkwood.net", "Legolas_Greenleaf", encoder.encode("password"), lothlorien, lindonGolfClub);
        legolas.setHandicap(Math.round(Math.random() * 30 * 10.0) / 10.0);

        User gimli = new User("gimli@erebor.com", "Gimli son of Gloin", encoder.encode("password"), moria, khazadDumGolfClub);
        gimli.setHandicap(Math.round(Math.random() * 30 * 10.0) / 10.0);

        User boromir = new User("boromir@gondor.gov", "Boromir son of Denethor", encoder.encode("password"), gondor, osgiliathGolfClub);
        boromir.setHandicap(Math.round(Math.random() * 30 * 10.0) / 10.0);

        userRepository.saveAll(Arrays.asList(frodo, sam, gandalf, aragorn, legolas, gimli, boromir));


    }

}
