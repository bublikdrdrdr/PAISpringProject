package tk.ubublik.pai.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Transaction.class)
public abstract class Transaction_ {

	public static volatile SingularAttribute<Transaction, Date> date;
	public static volatile SingularAttribute<Transaction, Long> amount;
	public static volatile SingularAttribute<Transaction, Account> receiver;
	public static volatile SingularAttribute<Transaction, Account> sender;
	public static volatile SingularAttribute<Transaction, Long> id;
	public static volatile SingularAttribute<Transaction, String> message;
	public static volatile SingularAttribute<Transaction, TransactionStatus> status;

	public static final String DATE = "date";
	public static final String AMOUNT = "amount";
	public static final String RECEIVER = "receiver";
	public static final String SENDER = "sender";
	public static final String ID = "id";
	public static final String MESSAGE = "message";
	public static final String STATUS = "status";

}

