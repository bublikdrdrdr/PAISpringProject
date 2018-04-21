package tk.ubublik.pai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.ubublik.pai.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
