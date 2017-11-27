package tracker.services;

import tracker.models.Project;
import tracker.models.AjaxResponses.TasksListGroupByStatus;
import tracker.models.Task;
import tracker.models.TaskStatus;

import java.util.List;

public interface TaskService {
    List<Task> findAll(Project project);
    List<Task> findLatest5();
    Task findById(int id);
    Task create(Task task, Project project);
    Task edit(Task task, TaskStatus taskStatus);
    void deleteById(int id);
    List<TasksListGroupByStatus> groupByStatus (Project project);
}
