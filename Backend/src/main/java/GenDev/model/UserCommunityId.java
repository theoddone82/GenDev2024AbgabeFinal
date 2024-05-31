package GenDev.model;


import java.io.Serializable;
import java.util.Objects;

public class UserCommunityId implements Serializable {
    private Long userId;
    private Long communityId;

    // Default constructor
    public UserCommunityId() {}

    public UserCommunityId(Long userId, Long communityId) {
        this.userId = userId;
        this.communityId = communityId;
    }

    // Getters, setters, hashCode, and equals methods
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCommunityId that = (UserCommunityId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(communityId, that.communityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, communityId);
    }
}