package tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tracker.models.Project;
import tracker.models.User;
import java.util.Date;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    @Query("SELECT p FROM Project p WHERE p.is_actual = 't' AND p.user = :user_id")
    List<Project> findAll(@Param("user_id")User user);
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Project p SET p.is_actual = 'f', p.date_closed = :date WHERE p.id = :id")
    void deleteById(@Param("id") int id, @Param("date") Date date);
}