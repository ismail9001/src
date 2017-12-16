package tracker.services;

import tracker.models.Project;
import tracker.models.User;
import java.util.List;

public interface ProjectService {
    Project findOne(int id);
    List<Project> findAll(User user);
    Project create(Project project);
    Project edit(Project project, String mainURL);
    void deleteById(int id);
}
