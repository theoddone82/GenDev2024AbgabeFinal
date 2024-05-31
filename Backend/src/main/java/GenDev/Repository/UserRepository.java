package GenDev.Repository;

import GenDev.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Page<User> findAllByOrderByScoreDescIdAsc(Pageable pageable);

    @Query("SELECT COUNT(u) FROM User u WHERE (u.score > (SELECT u2.score FROM User u2 WHERE u2.id = :userId)) OR (u.score = (SELECT u2.score FROM User u2 WHERE u2.id = :userId) AND u.id < :userId)")
    int getUserPositionById(Long userId);


    @Query("Select Count(u) from User u")
    int getCountOfUsers();
}