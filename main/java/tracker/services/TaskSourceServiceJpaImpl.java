package tracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tracker.models.TaskSource;
import tracker.repositories.TaskSourceRepository;

import java.util.List;

@Service
public class TaskSourceServiceJpaImpl implements TaskSourceService {

    @Autowired
    private TaskSourceRepository taskSourceRepository;

    @Override
    public List<TaskSource> findAll() {
        return this.taskSourceRepository.findAll();
    }
}
