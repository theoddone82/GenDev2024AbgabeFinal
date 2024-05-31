package GenDev.Repository;
import GenDev.model.Community;
import GenDev.model.User;
import GenDev.model.UserCommunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserCommunityRepository extends JpaRepository<UserCommunity, Long> {
    List<User> findUsersByCommunity(Community community);


    @Query("SELECT uc.community FROM UserCommunity uc WHERE uc.userId = :userId")
    List<Community> findCommunitiesByUserId(Long userId);

    @Query("SELECT uc.user FROM UserCommunity uc WHERE uc.communityId = :communityId ORDER BY uc.user.score DESC")
    List<User> findUsersByCommunityOrderByDesc(Long communityId);
}