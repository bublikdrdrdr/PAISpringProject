package tk.ubublik.pai.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Account.class)
public abstract class Account_ {

	public static volatile SingularAttribute<Account, User> owner;
	public static volatile SingularAttribute<Account, Boolean> deleted;
	public static volatile SingularAttribute<Account, Boolean> blocked;
	public static volatile ListAttribute<Account, Transaction> sentTransactions;
	public static volatile ListAttribute<Account, Transaction> receivedTransactions;
	public static volatile SingularAttribute<Account, Date> created;
	public static volatile SingularAttribute<Account, String> name;
	public static volatile SingularAttribute<Account, Long> id;

	public static final String OWNER = "owner";
	public static final String DELETED = "deleted";
	public static final String BLOCKED = "blocked";
	public static final String SENT_TRANSACTIONS = "sentTransactions";
	public static final String RECEIVED_TRANSACTIONS = "receivedTransactions";
	public static final String CREATED = "created";
	public static final String NAME = "name";
	public static final String ID = "id";

}

