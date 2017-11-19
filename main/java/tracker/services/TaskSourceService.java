package tracker.services;

import tracker.models.TaskSource;

import java.util.List;

public interface TaskSourceService {
    List<TaskSource> findAll();
}
