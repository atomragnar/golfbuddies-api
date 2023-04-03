package golf.mates.demo.entities;

import golf.mates.demo.dtos.request.PlayAdRegistrationDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class PlayAd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String adText;
    @ManyToOne
    @JoinColumn(name = "golf_club_id")
    private GolfClub golfClub;
    private Timestamp playTime;
    private boolean hasCar;
    private int amoutOfSlots;
    private int emptySlots;
    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;
    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    @OneToMany(mappedBy = "playAd", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<BookedSlot> BookedSlots = new LinkedHashSet<>();

    public PlayAd(PlayAdRegistrationDto playAdRegistrationDto) {
        this.playTime = playAdRegistrationDto.getTeeTime();
        this.hasCar = playAdRegistrationDto.isHasCar();
        this.amoutOfSlots = 4;
        this.emptySlots = 3;
    }

    public boolean isNew() {
        return this.id == null;
    }

}
