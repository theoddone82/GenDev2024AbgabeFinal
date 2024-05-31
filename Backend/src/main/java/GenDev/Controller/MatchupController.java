package GenDev.Controller;

import GenDev.Repository.TeamRepository;
import GenDev.Service.MatchupService;
import GenDev.model.FootballTeam;
import GenDev.model.Matchup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/matchups")
public class MatchupController {
    @Autowired
    private MatchupService matchupService;
    @Autowired
    private TeamRepository teamRepository;

    @GetMapping
    public ResponseEntity<List<Matchup>> getAllMatchups() {
        List<Matchup> matchups = matchupService.getAllMatchups();
        return ResponseEntity.ok(matchups);
    }
    @GetMapping("/currentMatchup")
    public ResponseEntity<Matchup> getCurrentMatchup() {
        Matchup matchup = matchupService.getCurrentMatchup();
        return ResponseEntity.ok(matchup);
    }
}