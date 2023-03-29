package golf.mates.demo.repositories;

import golf.mates.demo.entities.GolfClub;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GolfClubRepository extends CrudRepository<GolfClub, Long> {


    List<GolfClub> findAll();

    @Query("select g from GolfClub g where g.location.id = ?1")
    List<GolfClub> findByLocation_Id(Long id);





}
