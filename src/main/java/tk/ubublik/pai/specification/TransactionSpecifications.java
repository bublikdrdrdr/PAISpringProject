package tk.ubublik.pai.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import tk.ubublik.pai.entity.Account;
import tk.ubublik.pai.entity.Transaction;
import tk.ubublik.pai.entity.Transaction_;

import javax.persistence.criteria.Predicate;

public class TransactionSpecifications {

	public enum TransactionFor {RECEIVER_OR_SENDER, RECEIVER_AND_SENDER}

	public static Specification<Transaction> senderReceiverSpecification(
			@Nullable Account sender, @Nullable Account receiver, TransactionFor transactionFor) {
		return (Specification<Transaction>) (root, query, criteriaBuilder) -> {
			Predicate senderEqualPredicate = criteriaBuilder.equal(root.get(Transaction_.sender), sender);
			Predicate receiverEqualPredicate = criteriaBuilder.equal(root.get(Transaction_.receiver), receiver);
			if (sender != null && receiver != null) {
				switch (transactionFor) {
					case RECEIVER_OR_SENDER:
						return criteriaBuilder.or(senderEqualPredicate, receiverEqualPredicate);
					case RECEIVER_AND_SENDER:
						return criteriaBuilder.and(senderEqualPredicate, receiverEqualPredicate);
					default:
						throw new IllegalArgumentException("Unknown enum value: " + transactionFor);
				}
			} else {
				if (sender!=null) return senderEqualPredicate;
				if (receiver!=null) return receiverEqualPredicate;
				return criteriaBuilder.and();
			}
		};
	}

	public static Specification<Transaction> limitByAmount(@Nullable Long minAmount, @Nullable Long maxAmount){
		return null;
		//TODO
	}
}
