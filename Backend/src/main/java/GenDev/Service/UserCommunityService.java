package GenDev.Service;


import GenDev.Repository.CommunityRepository;
import GenDev.Repository.UserCommunityRepository;
import GenDev.Repository.UserRepository;
import GenDev.model.Community;
import GenDev.model.User;
import GenDev.model.UserCommunity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UserCommunityService {

    @Autowired
    private UserCommunityRepository userCommunityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommunityRepository communityRepository;
    public Community addUserToCommunity(Long userId, Long communityId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Community community = communityRepository.findById(communityId).orElseThrow(() -> new RuntimeException("Community not found"));

        UserCommunity userCommunity = new UserCommunity();
        userCommunity.setUserId(userId);
        userCommunity.setCommunityId(communityId);
        userCommunity.setUser(user);
        userCommunity.setCommunity(community);

        userCommunityRepository.save(userCommunity);
        return community;
    }
    public List<User> getUsersInCommunity(Long communityId) {
        Community community = communityRepository.findById(communityId).orElseThrow(() -> new RuntimeException("Community not found"));
        return userCommunityRepository.findUsersByCommunity(community);
    }

    public List<Community> getCommunitiesByUserId(Long userId) {
        return userCommunityRepository.findCommunitiesByUserId(userId);
    }

    public List<User> getUsersInCommunityOrderByDesc(Long communityId) {
        return userCommunityRepository.findUsersByCommunityOrderByDesc(communityId);
    }
}