package golf.mates.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GolfClub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String club;

    @ManyToOne(optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "golfClub")
    private Set<User> users = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "golfClub")
    private Set<GolfCourse> courses = new LinkedHashSet<>();


    public GolfClub(String club, Location location) {
        this.club = club;
        this.location = location;
    }

    public void setLocation(Location location) {

    }

    public boolean isNew() {
        return this.id == null;
    }



}
