package es.webapp13.porthub.repository;

import es.webapp13.porthub.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Page<User> findByCategory(String category, Pageable pageable);

    Optional<User> findById(String name);

    User findFirstByName(String name);

    @Query("SELECT u FROM User u WHERE u.name LIKE ?1%")
    Page<User> findSearching(String search, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.category=?1")
    List<User> findSimilarUser(String category);

    @Query("SELECT COUNT(u.creationDate) FROM User u WHERE MONTH(u.creationDate) = ?1 AND YEAR(u.creationDate) =?2 ")
    int countAllByCreationDate_Month(int month, int year);
}
