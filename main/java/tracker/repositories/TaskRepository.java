package tracker.repositories;

import tracker.models.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query("SELECT p FROM Task p LEFT JOIN FETCH p.author ORDER BY p.date DESC")
    List<Task> findLatest5Tasks(Pageable pageable);
}