package tracker.services;

import tracker.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User findByEmail(String email);
    Optional<User> findUserByResetToken(String resetToken);
    void saveUser(User user);
}