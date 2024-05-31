package GenDev.Controller;

import GenDev.Repository.GameRepository;
import GenDev.Service.BetService;
import GenDev.Service.UserService;
import GenDev.model.Bet;
import GenDev.model.Matchup;
import GenDev.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import static MQTT.MqttPublisher.sendmessage;

@RestController
@RequestMapping("/bets")
public class BetController {
    @Autowired
    private BetService betService;
    @Autowired
    private UserService userService;
    @Autowired
    private GameRepository gameRepository;
    @PostMapping
    public ResponseEntity<Bet> createBet(@RequestBody Bet bet) {
        Matchup m = gameRepository.findById(bet.getMatchupId()).orElse(null);
        LocalDateTime now = LocalDateTime.now();
        if(m.getStartTime().compareTo(now) < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }else {
            betService.createBet(bet);
            return ResponseEntity.status(HttpStatus.CREATED).body(bet);
        }
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<Bet>> getBetsByUserId(@PathVariable Long userId) {
        List<Bet> bets = betService.getBetsByUserId(userId);
        return ResponseEntity.ok(bets);
    }
    @GetMapping
    public ResponseEntity<List<Bet>> getAllBets() {
        List<Bet> bets = betService.getAllBets();
        return ResponseEntity.ok(bets);
    }
    @GetMapping("/{username}/bets")
    public ResponseEntity<List<Bet>> getBetsByUsername(@PathVariable String username) {
        List<Bet> bets = betService.getBetsByUsername(username); // Assuming your service can handle username
        return ResponseEntity.ok(bets);
    }
    @PostMapping("/{matchup_id}/{goals_team_1}/{goals_team_2}")
    public void evaluateBetts(@PathVariable Long matchup_id, @PathVariable int goals_team_1, @PathVariable int goals_team_2) {
        betService.evaluateBets(matchup_id, goals_team_1, goals_team_2, Math.abs(goals_team_1 - goals_team_2), goals_team_1 - goals_team_2);

        String message = "";

            Page<User> users = userService.getUsersSortedByScore(0, 100);
            for (User user : users) {
                message+= user.toString();
            }

        Logger logger = Logger.getLogger(BetController.class.getName());
            logger.info(message);



        sendmessage("leaderboard", message);
    }

    @GetMapping("/lots")
    public void createLotsOfBets() {
        Logger logger = Logger.getLogger(BetController.class.getName());
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            try{
            Bet bet = new Bet();
            bet.setMatchupId((long) 7);
            bet.setUserId((long) 14000+i);
            bet.setGoalsBetTeam1(random.nextInt(5));
            bet.setGoalsBetTeam2(random.nextInt(5));
            betService.createBet(bet);
            logger.info("Created bet " + i);
        }catch (Exception e){
                logger.info("Error creating bet " + i);
            }
        }
    }
}