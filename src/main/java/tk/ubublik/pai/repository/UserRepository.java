package tk.ubublik.pai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tk.ubublik.pai.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query("select u from User u where u.email = ?1")
    User findByEmail(String email);
}
