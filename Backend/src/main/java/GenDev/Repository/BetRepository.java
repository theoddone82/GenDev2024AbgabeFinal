package GenDev.Repository;


import GenDev.model.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {
    List<Bet> findByUserId(Long userId);

    List<Bet> findByMatchupId(Long matchupId);

    Optional<Object> findByUserIdAndMatchupId(Long userId, Long matchupId);
}