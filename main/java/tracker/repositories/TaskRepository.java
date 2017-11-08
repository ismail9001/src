package tracker.repositories;

import org.springframework.data.repository.query.Param;
import tracker.models.Project;
import tracker.models.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query("SELECT p FROM Task p where p.project = :id ORDER BY p.date DESC")
    List<Task> findAll(@Param("id") Project project);
}