package golf.mates.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GolfCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String course;
    int numOfHoles;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "golf_club_id")
    private GolfClub golfClub;
    @ManyToOne(optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    public GolfCourse(String course, int i, GolfClub golfClub, Location location) {
        this.course = course;
        this.numOfHoles = i;
        setGolfClub(golfClub);
        this.location = location;
    }

    public void setGolfClub(GolfClub golfClub) {
        this.golfClub = golfClub;
        golfClub.getCourses().add(this);
    }

}
