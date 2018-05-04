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
	long sentSummary(@Param("sender") Account sender, @Param("statusList") List<TransactionStatus> statusList);

	@Query("select sum(t.amount) from Transaction t where t.receiver = :receiver and t.status in :statusList")
	long receivedSummary(@Param("receiver") Account receiver, @Param("statusList") List<TransactionStatus> statusList);

	default long getAvailableBalance(Account account) {
		//WHY THIS DOESN'T WORK??
		/*return receivedSummary(account, Collections.singletonList(TransactionStatus.ACCEPTED))
				- sentSummary(account, Arrays.asList(TransactionStatus.ACCEPTED, TransactionStatus.SENT));*/
		long amount = 0;
		for (Transaction transaction: getAllByAccount(account)){
			if (transaction.getSender().getId().equals(account.getId())){
				if (transaction.getStatus()!=TransactionStatus.CANCELED) amount -= transaction.getAmount();
			} else if (transaction.getReceiver().getId().equals(account.getId()) && transaction.getStatus()==TransactionStatus.SENT){
				amount += transaction.getAmount();
			}
		}
		return amount;
	}

	@Query("select t from Transaction t join fetch t.sender join fetch t.receiver where t.sender = :account or t.receiver = :account")
	List<Transaction> getAllByAccount(@Param("account") Account account);
}
