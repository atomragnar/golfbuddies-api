package golf.mates.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class BookedSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "play_ad_id", nullable = false)
    private PlayAd playAd;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User bookedUser;

}
