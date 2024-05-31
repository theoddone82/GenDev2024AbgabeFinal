package GenDev.Controller;

import GenDev.Repository.UserCommunityRepository;
import GenDev.Service.CommunityService;
import GenDev.Service.MatchupService;
import GenDev.Service.UserCommunityService;
import GenDev.model.Community;
import GenDev.model.Matchup;
import GenDev.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@RestController
@RequestMapping("/communities")
public class CommunityController {
    @Autowired
    private UserCommunityService UserCommunityService;
    @Autowired
    private CommunityService communityService;
    @GetMapping
    public ResponseEntity<List<Community>> getAllCommunities() {
        List<Community> Communities = communityService.getAllCommunities();
        return ResponseEntity.ok(Communities);
    }
    @PostMapping
    public ResponseEntity<Community> createCommunity(@RequestBody Community community) {
        String name = community.getCommunityName();
        if(communityService.containsCommunity(name)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }else{
        Community newCommunity = communityService.createCommunity(community);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCommunity);
        }
    }
    //join community
    @PostMapping("/{user_id}/join/{community_id}")
    public ResponseEntity<Community> joinCommunity(@PathVariable Long community_id, @PathVariable Long user_id) {
        try {
            Community community = UserCommunityService.addUserToCommunity(community_id, user_id);
            return ResponseEntity.ok(community);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @GetMapping("/user/{userId}")
    public List<Community> getCommunitiesByUserId(@PathVariable Long userId) {
        return UserCommunityService.getCommunitiesByUserId(userId);
    }

    @GetMapping("/{communityId}/userRankings")
    public List<User> getUsersInCommunity(@PathVariable Long communityId) {
        return UserCommunityService.getUsersInCommunityOrderByDesc(communityId);
    }
}