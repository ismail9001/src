package tracker.services;

import tracker.models.Project;
import tracker.models.User;
import java.util.Optional;

public interface UserService {
    User findByEmail(String email);
    User findUserByResetToken(String resetToken);
    void saveUser(User user);
    void setWatchedProject (User user, Project watchedProject);
    void deleteWatchedProject(Project project);
}