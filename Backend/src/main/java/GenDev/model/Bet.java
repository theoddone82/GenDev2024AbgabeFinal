package GenDev.model;
import jakarta.persistence.*;

@Entity
@Table(name = "bets") // Specify the table name if it's different from the default
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bet_id")
    private Long betId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "matchup_id")
    private Long matchupId;

    @Column(name = "goals_bet_team1")
    private int goalsBetTeam1;

    @Column(name = "goals_bet_team2")
    private int goalsBetTeam2;

    public Bet(Long user_id, Long matchupId, int goalsBetTeam1, int goalsBetTeam2) {
        this.userId = user_id;
        this.matchupId = matchupId;
        this.goalsBetTeam1 = goalsBetTeam1;
        this.goalsBetTeam2 = goalsBetTeam2;
    }
    public Bet() {
    }

    // Getters and Setters
    public Long getBetId() {
        return betId;
    }

    public void setBetId(Long betId) {
        this.betId = betId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMatchupId() {
        return matchupId;
    }

    public void setMatchupId(Long matchupId) {
        this.matchupId = matchupId;
    }

    public int getGoalsBetTeam1() {
        return goalsBetTeam1;
    }

    public void setGoalsBetTeam1(int goalsBetTeam1) {
        this.goalsBetTeam1 = goalsBetTeam1;
    }

    public int getGoalsBetTeam2() {
        return goalsBetTeam2;
    }

    public void setGoalsBetTeam2(int goalsBetTeam2) {
        this.goalsBetTeam2 = goalsBetTeam2;
    }
}
