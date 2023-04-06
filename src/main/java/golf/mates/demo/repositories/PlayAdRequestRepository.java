package golf.mates.demo.repositories;

import golf.mates.demo.entities.PlayAdRequest;
import golf.mates.demo.utils.PlayAdRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayAdRequestRepository extends JpaRepository<PlayAdRequest, Long> {

    @Query("select p from PlayAdRequest p where p.playAd.creator.id = ?1")
    List<PlayAdRequest> findByPlayAd_Creator_Id(Long id);

    @Query("select p from PlayAdRequest p where p.requester.id = ?1")
    List<PlayAdRequest> findByRequester_Id(Long id);

    @Query("select p from PlayAdRequest p where p.playAd.id = ?1")
    List<PlayAdRequest> findByPlayAd_Id(Long id);

    @Query("select p from PlayAdRequest p where p.playAd.creator.id = ?1 and p.status = ?2 order by p.createdAt DESC")
    List<PlayAdRequest> findByPlayAd_Creator_IdAndStatusOrderByCreatedAtDesc(Long id, PlayAdRequestStatus status);










}