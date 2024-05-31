package GenDev.Service;

import GenDev.Repository.BetRepository;
import GenDev.model.Bet;
import GenDev.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class BetService {
    @Autowired
    private BetRepository betRepository;
    @Autowired
    private UserService userService;

    public Bet createBet(Bet bet) {
        if(betRepository.findByUserIdAndMatchupId(bet.getUserId(), bet.getMatchupId()).isPresent()){
            return null;
        }

        return betRepository.save(bet);
    }

    public List<Bet> getBetsByUserId(Long userId) {
        return betRepository.findByUserId(userId);
    }

    public List<Bet> getAllBets() {
        try {
            return betRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Bet> getBetsByUsername(String username) {
        User user = userService.getUserByUsername(username).orElse(null);
        if (user == null) {
            return null;
        }
        return betRepository.findByUserId(user.getId());
    }
    private static int[][] lookupTable = new int[100][100];

    public void evaluateBets(Long matchupId, int goalsTeam1, int  goalsTeam2,int goalDifference, int tendency) { // takes the input as result of a match then updates scores accordingly
        // Initialize lookup table with -1 indicating uncomputed values
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                lookupTable[i][j] = -1;
            }
        }

        List<Bet> bets = betRepository.findByMatchupId(matchupId);
        Logger logger = Logger.getLogger(BetService.class.getName());
        logger.info("Evaluating bets for matchup " + matchupId + " with result " + goalsTeam1 + " - " + goalsTeam2);

        for (Bet bet : bets) {
            int betGoalsTeam1 = bet.getGoalsBetTeam1();
            int betGoalsTeam2 = bet.getGoalsBetTeam2();
            int score = lookupTable[betGoalsTeam1][betGoalsTeam2];



            if (score != -1) {
                User user = userService.getUserById(bet.getUserId()).orElse(null);
                if (user == null) {
                    logger.info("This should never happen");
                    continue;
                }
                user.addToScore(score);
                userService.saveUser(user);
                betRepository.delete(bet);
                logger.info("Used lookup table for bet " + bet.getBetId() + " with score " + score);
                continue;
            }

            logger.info("Evaluating bet " + bet.getBetId() + " for user " + bet.getUserId());
            int betGoalDifference = Math.abs(betGoalsTeam1 - betGoalsTeam2);
            User user = userService.getUserById(bet.getUserId()).orElse(null);
            if (user == null) {
                logger.info("This should never happen");
                continue;
            }

            if (betGoalsTeam1 == goalsTeam1 && betGoalsTeam2 == goalsTeam2) {
                score = 8;
            } else if (betGoalDifference == goalDifference && goalDifference != 0) {
                score = 6;
            } else if (Math.signum(betGoalsTeam1 - betGoalsTeam2) == Math.signum(tendency) || (betGoalDifference == goalDifference && goalDifference == 0)) {
                score = 4;
            } else {
                score = 0;
            }

            lookupTable[betGoalsTeam1][betGoalsTeam2] = score;
            user.addToScore(score);
            userService.saveUser(user);
            betRepository.delete(bet);
        }
        lookupTable = new int[100][100];
    }
}