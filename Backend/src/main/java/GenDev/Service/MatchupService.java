package GenDev.Service;

import GenDev.Repository.GameRepository;
import GenDev.model.Matchup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchupService {
    @Autowired
    private GameRepository gameRepository;

    public List<Matchup> getAllMatchups() {
        return gameRepository.findAll();
    }

    public Matchup getCurrentMatchup() {
        return gameRepository.findAll().get(0);

    }
}