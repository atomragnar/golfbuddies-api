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

    @ManyToOne()
    @JoinColumn(name = "golf_club_id")
    private GolfClub golfClub;

    @OneToMany(mappedBy = "receiver")
    private List<Message> messagesRecived;

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
    private String role;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = "ROLE_USER";
    }

    public User(String username, String password, Location location) {
        this.username = username;
        this.password = password;
        this.location = location;
        this.role = "ROLE_USER";
    }


    public User(UserRegistrationDto userRegistrationDto) {
        this.username = userRegistrationDto.getUsername();
        this.password = userRegistrationDto.getPassword();
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
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
