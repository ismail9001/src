package tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tracker.models.Project;
import tracker.models.User;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    @Query("SELECT p FROM Project p LEFT JOIN FETCH p.user where p.user = :user")
    List<Project> findAll(@Param("user")User user);
}