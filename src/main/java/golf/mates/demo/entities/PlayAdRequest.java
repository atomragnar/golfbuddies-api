package golf.mates.demo.entities;

import golf.mates.demo.utils.PlayAdRequestStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class PlayAdRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private User requester;

    @ManyToOne
    @JoinColumn
    private PlayAd playAd;

    private int slotIndex;

    //private boolean approved;
    PlayAdRequestStatus status;

    LocalDateTime createdAt;

    public PlayAdRequest(User requester, PlayAd playAd) {
        if (!(requester.getUsername().equals(playAd.getCreator().getUsername()))) {
            this.requester = requester;
            setPlayAd(playAd);
            this.status = PlayAdRequestStatus.PENDING;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        setCreatedTimeForRequest();
    }

    public void setPlayAd(PlayAd playAd) {
        this.playAd = playAd;
        playAd.getRequests().add(this);
        this.slotIndex = playAd.getRequests().size() - 1;
    }

    public void setCreatedTimeForRequest() {
        this.setCreatedAt(LocalDateTime.now());
    }

    public void setPlayAdRequestApproved() {
        this.status = PlayAdRequestStatus.APPROVED;
        this.playAd.addPlayer(this.requester);
    }

}