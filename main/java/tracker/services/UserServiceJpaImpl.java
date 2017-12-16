package tracker.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tracker.models.Project;
import tracker.models.Role;
import tracker.models.User;
import tracker.repositories.RoleRepository;
import tracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

@Service
@Primary
public class UserServiceJpaImpl implements UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public User findByEmail(String email) {
        return this.userRepo.findByEmail(email);
    }

    @Override
    public User findUserByResetToken(String resetToken) {
        return userRepo.findByResetToken(resetToken);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setCreatedOn(new Date());
        Role userRole = roleRepo.findByRole("ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepo.save(user);
    }

    @Override
    public void setWatchedProject(User user, Project watchedProject) {
        user.setWatched_project(watchedProject);
        userRepo.save(user);
    }

    @Override
    public void deleteWatchedProject(Project project) {
        this.userRepo.deleteWatchedProject(project);
    }
}