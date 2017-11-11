package tracker.services;

import tracker.models.TaskStatus;
import java.util.List;

public interface TaskStatusService {
    List<TaskStatus> findAll();
}
