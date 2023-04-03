package golf.mates.demo.entities;

import golf.mates.demo.utils.PlayAdRequestStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class PlayAdRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User requester;

    @ManyToOne(fetch = FetchType.LAZY)
    private PlayAd playAd;

    private int slotIndex;

    //private boolean approved;
    PlayAdRequestStatus status;

    LocalDateTime createdAt;

    public PlayAdRequest(User requester, PlayAd playAd, int slotIndex) {
        this.requester = requester;
        this.playAd = playAd;
        this.slotIndex = slotIndex;
        this.status = PlayAdRequestStatus.PENDING;
    }

    public void setCreatedTimeForRequest() {
        this.setCreatedAt(LocalDateTime.now());
    }

}