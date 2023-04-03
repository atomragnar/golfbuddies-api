package golf.mates.demo.repositories;

import golf.mates.demo.entities.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface LocationRepository extends CrudRepository <Location, Long> {

    @Override
    Optional<Location> findById(Long aLong);

    List<Location> findAll();



}
