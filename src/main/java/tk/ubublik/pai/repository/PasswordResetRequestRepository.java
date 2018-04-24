package tk.ubublik.pai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.ubublik.pai.entity.PasswordResetRequest;

public interface PasswordResetRequestRepository extends JpaRepository<PasswordResetRequest, Long> {
}
