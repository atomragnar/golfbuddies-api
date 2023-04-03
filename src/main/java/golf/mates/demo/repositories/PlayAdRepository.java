package golf.mates.demo.repositories;

import golf.mates.demo.entities.PlayAd;
import golf.mates.demo.entities.PlayAdRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface PlayAdRepository extends JpaRepository<PlayAd, Long> {

    @Query("SELECT p FROM PlayAdRequest p WHERE p.playAd = :playAd")
    List<PlayAdRequest> findAllByPlayAd(@Param("playAd") PlayAd playAd);

    @Override
    Optional<PlayAd> findById(Long aLong);



}
