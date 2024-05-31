package GenDev.Service;

import GenDev.model.User;
import GenDev.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public User createUser(User user) {
        // Here you can perform any necessary validation or business logic
        // before saving the user to the database
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            return null;
        }
        return userRepository.save(user);
    }
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Page<User> getUsersSortedByScore(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAllByOrderByScoreDescIdAsc(pageable);
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public Page<User> getUsersSortedByScoreDesc(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("score").ascending());
        return userRepository.findAll(pageable);
    }

    public int getUserPositionById(Long userId) {
        if(userRepository.findById(userId).isEmpty()){
            return -1000;
        }
        return userRepository.getUserPositionById(userId);
    }

    public int worstPlayerIndex() {
        return userRepository.getCountOfUsers();
    }
}