package tracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tracker.models.Task;
import tracker.repositories.TaskRepository;

import java.util.List;

@Service
@Primary
public class TaskServiceJpaImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepo;
    @Override
    public List<Task> findAll() {
        return this.taskRepo.findAll();
    }
    @Override
    public List<Task> findLatest5() {
        return this.taskRepo.findLatest5Tasks(new PageRequest(0, 5));
    }
    @Override
    public Task findById(int id) {
        return this.taskRepo.findOne(id);
    }
    @Override
    public Task create(Task task) {
        return this.taskRepo.save(task);
    }
    @Override
    public Task edit(Task post) {
        return this.taskRepo.save(post);
    }
    @Override
    public void deleteById(int id) {
        this.taskRepo.delete(id);
    }
}