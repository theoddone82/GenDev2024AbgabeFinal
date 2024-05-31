package GenDev.model;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "score")
    private int score;

    @Column(nullable = false, unique = true)
    private String username;

    // Constructors, getters, and setters
    public User() {}

    public User(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void addToScore(int points) {
        this.score += points;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", score=" + score +
                ", username='" + username + '\'' +
                '}';
    }
}