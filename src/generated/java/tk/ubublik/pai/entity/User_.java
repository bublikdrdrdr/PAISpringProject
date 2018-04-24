package tk.ubublik.pai.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, Date> passwordChanged;
	public static volatile SingularAttribute<User, Role> role;
	public static volatile ListAttribute<User, PasswordResetRequest> passwordResetRequests;
	public static volatile ListAttribute<User, Block> blocks;
	public static volatile SingularAttribute<User, Date> registered;
	public static volatile SingularAttribute<User, Long> id;
	public static volatile ListAttribute<User, Account> accounts;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, Boolean> enabled;
	public static volatile SingularAttribute<User, String> username;

	public static final String PASSWORD = "password";
	public static final String PASSWORD_CHANGED = "passwordChanged";
	public static final String ROLE = "role";
	public static final String PASSWORD_RESET_REQUESTS = "passwordResetRequests";
	public static final String BLOCKS = "blocks";
	public static final String REGISTERED = "registered";
	public static final String ID = "id";
	public static final String ACCOUNTS = "accounts";
	public static final String EMAIL = "email";
	public static final String ENABLED = "enabled";
	public static final String USERNAME = "username";

}

