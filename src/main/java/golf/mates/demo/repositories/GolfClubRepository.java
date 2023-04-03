package golf.mates.demo.repositories;

import golf.mates.demo.entities.GolfClub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GolfClubRepository extends JpaRepository<GolfClub, Long> {


    @Override
    Optional<GolfClub> findById(Long aLong);

    List<GolfClub> findAll();

    @Query("select g from GolfClub g where g.location.id = ?1")
    List<GolfClub> findByLocation_Id(Long id);







}
