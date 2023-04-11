package golf.mates.demo.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import golf.mates.demo.utils.PlayAdRequestStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

/*    @Column(name = "golfClub_id", insertable = false, updatable = false)
    private Long golfClubId;*/

    @ManyToOne
    @JoinColumn(name = "course_id")
    private GolfCourse course;
  /*  @Column(name = "course_id", insertable = false, updatable = false)
    private Long courseId;*/
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
    /*@Column(name = "location_id", insertable = false, updatable = false)
    private Long locationId;*/
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "playAd", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlayAdRequest> requests = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "playAd", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlayAdSlot> slots = new ArrayList<>(4);

    private LocalDateTime createdAt;
    private LocalDateTime teeTime;
    private boolean hasCar;


    //Duration duration;  kan ha denna för ev. ha tid for 18 hål.
    // LocalDateTime endDateTime = startDateTime.plus(duration);

    public PlayAd(User creator) {
        this.creator = creator;
        for (int i = 0; i < 3; i++) {
            this.slots.add(new PlayAdSlot(this, i + 1));
        }
        setCreatedTimeForAd();
        this.totalHandicap = creator.getHandicap();
        this.currentlyFilledSlot = 1;
        this.handicapAverage = getHandicapAverage();
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

    private double currentlyFilledSlot;
    private double totalHandicap;
    private double handicapAverage;



    // kan använda denna för clean up ibland, men tror inte det behövs, utan kan söka specifikt på PENDING requests bara.

    public void checkAndCleanupRequests() {
        boolean allFilledAndApproved = slots.stream()
                .noneMatch(PlayAdSlot::isEmpty);
        if (allFilledAndApproved) {
            requests.removeIf(request -> request.getStatus() != PlayAdRequestStatus.APPROVED);
        }
    }

    public double getHandicapAverage() {
        return this.totalHandicap / this.currentlyFilledSlot;
    }

    public void addPlayer(User player) {
        for (PlayAdSlot slot : slots) {
            if (!slot.isEmpty()) {
                if (slot.hasPlayer(player.getId())) {
                    break;
                }
            }
            if (slot.isEmpty()) {
                slot.setPlayer(player);
                this.currentlyFilledSlot++;
                this.totalHandicap += player.getHandicap();
                break;
            }
        }
        this.handicapAverage = getHandicapAverage();
    }

    public void removePlayer(Long userId) {
        for (int i = 0; i < slots.size(); i++) {
            PlayAdSlot slot = slots.get(i);
            if (slot.hasPlayer(userId)) {
                System.out.println("Found a match");
                this.currentlyFilledSlot--;
                this.totalHandicap -= slot.getPlayer().getHandicap();
                slot.removePlayer();
                System.out.println(slot.getPlayer());
                break;
            }
        }
        this.handicapAverage = getHandicapAverage();
    }

}