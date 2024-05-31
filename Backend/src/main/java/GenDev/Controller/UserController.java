package GenDev.Controller;

import GenDev.model.User;
import GenDev.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.getUserByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/sorted")
    public ResponseEntity<Page<User>> getUsersSortedByScore(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10000") int size) {
        Page<User> users = userService.getUsersSortedByScore(page, size);
        return ResponseEntity.ok(users);
    }
    @GetMapping("/sortedAsc")
    public ResponseEntity<Page<User>> getUsersSortedByScoreAsc(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100000") int size) {
        Page<User> users = userService.getUsersSortedByScoreDesc(page, size);
        return ResponseEntity.ok(users);
    }
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam String username) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            User newUser = new User(username);
            User createdUser = userService.createUser(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        }
    }
    @GetMapping("/{userId}/position")
    public ResponseEntity<Integer> getUserPositionById(@PathVariable Long userId) {
        int position = userService.getUserPositionById(userId);
        return ResponseEntity.ok(position+1);
    }
    @GetMapping("/Million")
    public void createUsers() {
        Logger logger = Logger.getLogger(UserController.class.getName());
        for (int i = 0; i < 1000000; i++) {
            User user = new User("user" + i);
            userService.createUser(user);
            logger.info("User created: user" +i);
        }
    }
    @GetMapping("/worst")
    public ResponseEntity<Integer> worstPlayerIndex() {
        int worstPlayerIndex = userService.worstPlayerIndex();
        return ResponseEntity.ok(worstPlayerIndex);
    }

}