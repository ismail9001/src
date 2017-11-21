package tracker.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tracker.models.Project;
import tracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    @Query("SELECT u FROM User u where u.resetToken= :resetToken")
    Optional <User> findByResetToken(@Param("resetToken") String resetToken);
    @Query("UPDATE User u SET u.watched_project = 'NULL' WHERE u.watched_project = :id")//не пашет запрос
    void deleteWatchedProject(@Param("id") Project project);
}
