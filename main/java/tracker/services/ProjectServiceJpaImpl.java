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
public class ProjectServiceJpaImpl extends UserServiceJpaImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepo;
    @Override
    public Project findById(int id) {
        return this.projectRepo.findOne(id);
    }

    @Override
    public List<Project> findAll(User user) {
        return this.projectRepo.findAll(user);
    }

    @Override
    public Project create(Project project) {
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        project.setUser(findByEmail(currentUser));
        project.setDate_created(new Date());
        return this.projectRepo.save(project);
    }

    @Override
    public Project edit(Project post) {
            return this.projectRepo.save(post);
    }

    @Override
    public void deleteById(int id) {
            Date date = new Date();
            this.projectRepo.deleteById(id, date);
    }
}
