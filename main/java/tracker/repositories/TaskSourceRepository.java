package tracker.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tracker.models.TaskSource;

import java.util.List;

@Repository
public interface TaskSourceRepository extends JpaRepository<TaskSource, Integer> {
    @Query("SELECT t FROM TaskSource t")
    List<TaskSource> findAll();

    TaskSource findBySource(String source);
}
