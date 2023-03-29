package golf.mates.demo.repositories;

import golf.mates.demo.entities.GolfCourse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GolfCourseRepository extends CrudRepository<GolfCourse, Long> {
}
