package golf.mates.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import golf.mates.demo.dtos.request.UserRegistrationDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private double handicap;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "bookedUser", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<BookedSlot> BookedSlots = new LinkedHashSet<>();

    @OneToMany(mappedBy = "receiver")
    private List<Message> messagesReceived;

    @OneToMany(mappedBy = "sender")
    private List<Message> messagesSended;

    @JsonIgnore
    private boolean accountExpired = false;
    @JsonIgnore
    private boolean accountLocked = false;
    @JsonIgnore
    private boolean credentialsExpired = false;
    @JsonIgnore
    private boolean accountEnabled = true;
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;
    @UpdateTimestamp
    private Timestamp lastModifiedDate;
    private String role = "ROLE_USER";

    @ManyToOne
    @JoinColumn(name = "golf_club_id")
    private GolfClub golfClub;
    private String sex="Man";

    public User(String username, String password, double handicap) {
        this.username = username;
        this.password = password;
        this.handicap = handicap;
        this.role = "ROLE_USER";
    }

    public User(String username, String password, Location location, double handicap) {
        this.username = username;
        this.password = password;
        this.location = location;
        this.handicap = handicap;
        this.role = "ROLE_USER";

    }


    public User(UserRegistrationDto userRegistrationDto) {
        this.username = userRegistrationDto.getUsername();
        this.password = userRegistrationDto.getPassword();
        this.handicap = userRegistrationDto.getHandicap();
        this.sex = userRegistrationDto.getSex();
    }

    public User(String username, String password, Location location, GolfClub golfClub, double handicap,String sex) {
        this.username = username;
        this.password = password;
        this.location = location;
        this.handicap = handicap;
        setGolfClub(golfClub);
        this.role = "ROLE_USER";
        this.sex = sex;
    }

    public void setGolfClub(GolfClub golfClub) {
        this.golfClub = golfClub;
        golfClub.getUsers().add(this);
    }

    public boolean isNew() {
        return this.id == null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
