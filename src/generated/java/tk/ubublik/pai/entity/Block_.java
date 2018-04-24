package tk.ubublik.pai.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Block.class)
public abstract class Block_ {

	public static volatile SingularAttribute<Block, String> reason;
	public static volatile SingularAttribute<Block, Date> start;
	public static volatile SingularAttribute<Block, User> admin;
	public static volatile SingularAttribute<Block, Date> end;
	public static volatile SingularAttribute<Block, Long> id;
	public static volatile SingularAttribute<Block, User> user;

	public static final String REASON = "reason";
	public static final String START = "start";
	public static final String ADMIN = "admin";
	public static final String END = "end";
	public static final String ID = "id";
	public static final String USER = "user";

}

