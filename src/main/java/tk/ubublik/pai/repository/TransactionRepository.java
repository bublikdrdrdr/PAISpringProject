package tk.ubublik.pai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tk.ubublik.pai.entity.Account;
import tk.ubublik.pai.entity.Transaction;
import tk.ubublik.pai.entity.TransactionStatus;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {

	@Query("select sum(t.amount) from Transaction t where t.sender = :sender and t.status in :statusList")
	long sentSummary(@Param("sender") Account sender, @Param("statusList")List<TransactionStatus> statusList);

	@Query("select sum(t.amount) from Transaction t where t.receiver = :receiver and t.status in :statusList")
	long receivedSummary(@Param("receiver") Account receiver, @Param("statusList")List<TransactionStatus> statusList);

	default long getAvailableBalance(Account account){
		return receivedSummary(account, Collections.singletonList(TransactionStatus.ACCEPTED))
				- sentSummary(account, Arrays.asList(TransactionStatus.ACCEPTED, TransactionStatus.SENT));
	}
}
