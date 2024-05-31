package GenDev.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "matchups")
public class Matchup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchupId;

    @Column(name = "team1_id")
    private int team1;

    @Column(name = "team2_id")
    private int team2;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    // Getters and Setters
    public Long getMatchupId() {
        return matchupId;
    }

    public void setMatchupId(Long matchupId) {
        this.matchupId = matchupId;
    }

    public int getTeam1() {
        return team1;
    }

    public void setTeam1(int team1) {
        this.team1 = team1;
    }

    public int getTeam2() {
        return team2;
    }

    public void setTeam2(int team2) {
        this.team2 = team2;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}
