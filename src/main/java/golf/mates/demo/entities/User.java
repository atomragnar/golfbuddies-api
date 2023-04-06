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
    private String email;
    private String username;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender = GenderEnum.OKÄNT;
    @JsonIgnore
    private String password;
    private double handicap = 36.0;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "location_id", insertable = false, updatable = false)
    private Long locationId;
    @JsonIgnore
    @OneToMany(mappedBy = "player", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<PlayAdSlot> playAdSlots = new LinkedHashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "user1", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Conversation> conversations1 = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "user2", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Conversation> conversations2 = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messagesSent = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messagesReceived = new ArrayList<>();

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "golf_club_id")
    private GolfClub golfClub;


    public User(UserRegistrationDto userRegistrationDto) {
        this.username = userRegistrationDto.getUsername();
        this.email = userRegistrationDto.getEmail();
        this.password = userRegistrationDto.getPassword();
        this.handicap = 36.0;
    }

    public User(String email, String username, String password, Location location, GolfClub golfClub) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.location = location;
        setGolfClub(golfClub);
        this.role = "ROLE_USER";
    }

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
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
