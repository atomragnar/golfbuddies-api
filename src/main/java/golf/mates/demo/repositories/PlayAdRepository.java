package golf.mates.demo.repositories;

import golf.mates.demo.entities.PlayAd;
import golf.mates.demo.entities.PlayAdRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayAdRepository extends JpaRepository<PlayAd, Long> {

    @Query("SELECT p FROM PlayAdRequest p WHERE p.playAd = :playAd")
    List<PlayAdRequest> findAllByPlayAd(@Param("playAd") PlayAd playAd);

    @Override
    Optional<PlayAd> findById(Long aLong);

    @Query("select p from PlayAd p where p.creator.id = ?1")
    List<PlayAd> findByCreator_Id(Long id);

    List<PlayAd> findAllBy();

    @Query("select p from PlayAd p where p.course.id = ?1")
    List<PlayAd> findByCourse_Id(Long id);

    @Query("select p from PlayAd p where p.golfClub.id = ?1")
    List<PlayAd> findByGolfClub_Id(Long id);

    @Query("select p from PlayAd p where p.location.id = ?1")
    List<PlayAd> findByLocation_Id(Long id);

    @Query("select p from PlayAd p where p.creator.handicap between ?1 and ?2")
    List<PlayAd> findByCreator_HandicapBetween(double handicapStart, double handicapEnd);

    @Query("select p from PlayAd p inner join p.slots slots where slots.player.id = ?1")
    List<PlayAd> findBySlots_Player_Id(Long id);









}
