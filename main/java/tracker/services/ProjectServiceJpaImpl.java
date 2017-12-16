package tracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tracker.models.Project;
import tracker.models.User;
import tracker.repositories.ProjectRepository;
import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceJpaImpl  implements ProjectService {

    @Autowired
    private ProjectRepository projectRepo;
    @Autowired
    private UserService userService;
    @Override
    public Project findOne(int id) {
        return this.projectRepo.findOne(id);
    }

    @Override
    public List<Project> findAll(User user) {
        return this.projectRepo.findAll(user);
    }

    @Override
    public Project create(Project project) {
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        project.setUser(userService.findByEmail(currentUser));
        project.setIs_actual(true);
        project.setDate_created(new Date());
        return this.projectRepo.save(project);
    }

    @Override
    public Project edit(Project project, String projectName) {
        project.setProjectName(projectName);
        return this.projectRepo.save(project);
    }

    @Override
    public void deleteById(int id) {
            this.projectRepo.deleteById(id, new Date());
    }
}
