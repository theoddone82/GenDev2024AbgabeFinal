package GenDev.Repository;


import GenDev.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {

    Optional<Object> findByCommunityName(String name);
}