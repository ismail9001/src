package tracker.services;

import tracker.models.Project;

import java.util.List;

public interface ProjectService {
    Project findById(int id);
    List<Project> findAll();
    Project create(Project project);
    Project edit(Project project);
    void deleteById(int id);
}