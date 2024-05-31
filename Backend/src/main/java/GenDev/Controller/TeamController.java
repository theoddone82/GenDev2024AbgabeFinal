package GenDev.Controller;


import GenDev.Service.TeamService;
import GenDev.model.FootballTeam;
import GenDev.model.Matchup;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@RestController
@RequestMapping("/teams")
public class TeamController {


    @Autowired
    TeamService teamService;
    @GetMapping
    public ResponseEntity<List<FootballTeam>> getTeams() {
        List<FootballTeam> teams = (List<FootballTeam>) teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }
    @GetMapping("/{teamId}")
    public ResponseEntity<String> getTeamById(@PathVariable Long teamId) {
        Logger logger = Logger.getLogger(TeamController.class.getName());
        logger.info("Getting team by id: " + teamId);
        String teamname = teamService.getTeamById(teamId);
        return  ResponseEntity.ok(teamname);
    }
}
