package tracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tracker.models.Project;
import tracker.models.Task;
import tracker.repositories.TaskRepository;

import java.util.Date;
import java.util.List;

@Service
@Primary
public class TaskServiceJpaImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepo;
    @Autowired
    private UserService userService;
    @Override
    public List<Task> findAll(Project project) {
        return this.taskRepo.findAll(project);
    }
    @Override
    public List<Task> findLatest5() {
        return this.taskRepo.findAll();
    }
    @Override
    public Task findById(int id) {
        return this.taskRepo.findOne(id);
    }
    @Override
    public Task create(Task task, Project project) {
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        task.setAuthor(userService.findByEmail(currentUser));
        task.setDate_created(new Date());
        task.setIs_actual(true);
        task.setProject(project);
        return this.taskRepo.save(task);
    }
    @Override
    public Task edit(Task post) {
        return this.taskRepo.save(post);
    }
    @Override
    public void deleteById(int id) {
        this.taskRepo.deleteById(id, new Date());
    }
}