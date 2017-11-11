package tracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tracker.models.TaskStatus;
import tracker.repositories.TaskStatusRepository;

import java.util.List;

@Service
public class TaskStatusServiceJpaImpl implements TaskStatusService {

    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @Override
    public List<TaskStatus> findAll() {
        return this.taskStatusRepository.findAll();
    }
}
