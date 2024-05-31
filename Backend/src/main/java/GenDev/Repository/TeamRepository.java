package GenDev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import GenDev.model.FootballTeam;

@Repository
public interface TeamRepository extends JpaRepository<FootballTeam, Long> {
    FootballTeam findByTeamId(Long teamId);
}
