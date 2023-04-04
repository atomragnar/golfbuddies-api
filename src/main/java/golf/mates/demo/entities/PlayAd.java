package golf.mates.demo.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import golf.mates.demo.utils.PlayAdRequestStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class PlayAd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;
    @Column(name = "creator_id", insertable = false, updatable = false)
    private Long creatorId;
    @ManyToOne
    @JoinColumn(name = "golfClub_id")
    private GolfClub golfClub;
    @Column(name = "golfClub_id", insertable = false, updatable = false)
    private Long golfClubId;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private GolfCourse course;
    @Column(name = "course_id", insertable = false, updatable = false)
    private Long courseId;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
    @Column(name = "location_id", insertable = false, updatable = false)
    private Long locationId;
    @OneToMany(mappedBy = "playAd", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlayAdRequest> requests = new ArrayList<>();

    @OneToMany(mappedBy = "playAd", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlayAdSlot> slots = new ArrayList<>(4);

    LocalDateTime createdAt;
    LocalDateTime startDateTime;


    //Duration duration;  kan ha denna för ev. ha tid for 18 hål.
    // LocalDateTime endDateTime = startDateTime.plus(duration);

    public PlayAd(User creator) {
        this.creator = creator;
        for (int i = 0; i < 3; i++) {
            this.slots.add(new PlayAdSlot(this, i + 1));
        }
        setCreatedTimeForAd();
    }

    public void setCreatedTimeForAd() {
        this.setCreatedAt(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public User getCreator() {
        return creator;
    }

    @JsonIgnore
    public List<PlayAdRequest> getRequests() {
        return requests;
    }

    @JsonIgnore
    public List<PlayAdSlot> getSlots() {
        return slots;
    }


    // kan använda denna för clean up ibland, men tror inte det behövs, utan kan söka specifikt på PENDING requests bara.

    public void checkAndCleanupRequests() {
        boolean allFilledAndApproved = slots.stream()
                .noneMatch(PlayAdSlot::isEmpty);
        if (allFilledAndApproved) {
            requests.removeIf(request -> request.getStatus() != PlayAdRequestStatus.APPROVED);
        }
    }

    public void addPlayer(User player) {
        for (PlayAdSlot slot : slots) {
            if (slot.isEmpty()) {
                slot.setPlayer(player);
                break;
            }
        }
    }

    public void removePlayer(String username) {
        for (PlayAdSlot slot : slots) {
            if (slot.hasPlayer(username)) {
                slot.removePlayer();
                break;
            }
        }
    }

}