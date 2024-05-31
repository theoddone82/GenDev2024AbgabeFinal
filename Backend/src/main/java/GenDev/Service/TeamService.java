package GenDev.Service;

import GenDev.Repository.TeamRepository;
import GenDev.model.FootballTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;
    public String getTeamById(Long teamId) {
        FootballTeam team = teamRepository.findByTeamId(teamId);
        Logger logger = Logger.getLogger(TeamService.class.getName());
        logger.info("Getting team by id: " + teamId);
        if (team == null) {
            return "Team not found";
        }
        return team.getTeamName();
    }

    public Object getAllTeams() {
        return teamRepository.findAll();
    }
}
