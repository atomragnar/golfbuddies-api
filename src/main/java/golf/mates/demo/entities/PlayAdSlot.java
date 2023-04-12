package golf.mates.demo.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class PlayAdSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User player;

    @ManyToOne(fetch = FetchType.LAZY)
    private PlayAd playAd;
    private int slotIndex;

    public PlayAdSlot(PlayAd playAd, int i) {
        this.playAd = playAd;
        this.slotIndex = i;
    }

    public boolean isEmpty() {
        return this.player == null;
    }

    public boolean hasPlayer(Long userId) {
        return Objects.equals(this.player.getId(), userId);
    }

    public void removePlayer() {
        this.player = null;
    }

    // constructors, getters, setters
}