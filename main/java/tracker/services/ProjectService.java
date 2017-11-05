package tracker.services;

import tracker.models.Project;
import tracker.models.User;

import java.util.List;

public interface ProjectService {
    Project findById(int id);
    List<Project> findAll(User user);
    Project create(Project project);
    Project edit(Project project);
    void deleteById(int id);
}
