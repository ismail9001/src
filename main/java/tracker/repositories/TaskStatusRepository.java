package tracker.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tracker.models.TaskStatus;

import java.util.List;

@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatus, Integer> {
    @Query("SELECT t FROM TaskStatus t")
    List<TaskStatus> findAll();

    TaskStatus findByStatus(String status);
}
