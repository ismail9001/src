package tracker.services;

import tracker.models.Task;
import java.util.List;

public interface TaskService {
    List<Task> findAll();
    List<Task> findLatest5();
    Task findById(int id);
    Task create(Task task);
    Task edit(Task task);
    void deleteById(int id);
}
