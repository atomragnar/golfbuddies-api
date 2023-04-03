package golf.mates.demo.entities;


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

    @ManyToOne(fetch = FetchType.LAZY)
    private User creator;

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

    public List<PlayAdRequest> getRequests() {
        return requests;
    }

    public List<PlayAdSlot> getSlots() {
        return slots;
    }

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

    public void removePlayer(User player) {
        for (PlayAdSlot slot : slots) {
            if (slot.hasPlayer(player)) {
                slot.removePlayer();
                break;
            }
        }
    }

}