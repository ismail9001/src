package tracker.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import tracker.models.Project;
import tracker.models.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query("SELECT t FROM Task t where t.project = :id and t.is_actual = 't' ORDER BY t.date_created DESC")
    List<Task> findAll(@Param("id") Project project);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Task t SET t.is_actual = 'f', t.date_closed = :date WHERE t.id = :id")
    void deleteById(@Param("id") int id, @Param("date") Date date);
    //запрос для вывода задач по статусам
    //@Query("SELECT count(*), ts.status FROM Task t JOIN TaskStatus ts on t.taskStatus = ts.id where t.project = :id and t.is_actual = 't' GROUP BY ts.status")
    //select "count"(*), ts.status from tasks t JOIN task_status ts on t.task_status_id = ts."id" group by ts.status
    //HashMap<Integer, String> groupByStatus(@Param("id") Project project);
}