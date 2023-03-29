package golf.mates.demo.repositories;

import golf.mates.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsernameIgnoreCase(String username);

    Optional<User> findByUsernameIgnoreCase(String username);

    @Query("select u from User u where u.location.id = ?1 order by u.username")
    List<User> findByLocation_IdOrderByUsernameAsc(Long id);

    @Query("select u from User u where u.golfClub.id = ?1 order by u.username")
    List<User> findByGolfClub_IdOrderByUsernameAsc(Long id);






}
