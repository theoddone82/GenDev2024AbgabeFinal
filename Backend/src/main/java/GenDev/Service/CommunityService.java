package GenDev.Service;

import GenDev.Repository.BetRepository;
import GenDev.Repository.CommunityRepository;
import GenDev.model.Bet;
import GenDev.model.Community;
import GenDev.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityService {
    @Autowired
    private CommunityRepository communityRepository;

    public List<Community> getAllCommunities() {
        try {
            return communityRepository.findAll();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Community createCommunity(Community community) {
        return communityRepository.save(community);
    }

    public boolean containsCommunity(String name) {
        return communityRepository.findByCommunityName(name).isPresent();
    }
}
