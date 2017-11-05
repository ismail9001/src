package tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tracker.models.Project;
import tracker.models.User;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    @Query("SELECT p FROM Project p where p.id = :user_id")
    List<Project> findAll(@Param("user_id")int id);
}