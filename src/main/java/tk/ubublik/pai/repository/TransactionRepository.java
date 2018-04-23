package tk.ubublik.pai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.ubublik.pai.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
