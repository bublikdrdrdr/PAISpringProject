package tk.ubublik.pai.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PasswordResetRequest.class)
public abstract class PasswordResetRequest_ {

	public static volatile SingularAttribute<PasswordResetRequest, Date> date;
	public static volatile SingularAttribute<PasswordResetRequest, Long> id;
	public static volatile SingularAttribute<PasswordResetRequest, User> user;
	public static volatile SingularAttribute<PasswordResetRequest, String> hash;

	public static final String DATE = "date";
	public static final String ID = "id";
	public static final String USER = "user";
	public static final String HASH = "hash";

}

