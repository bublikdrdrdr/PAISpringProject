package tk.ubublik.pai.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Rate.class)
public abstract class Rate_ {

	public static volatile SingularAttribute<Rate, Date> date;
	public static volatile SingularAttribute<Rate, Double> buying;
	public static volatile SingularAttribute<Rate, Double> selling;
	public static volatile SingularAttribute<Rate, Long> id;

	public static final String DATE = "date";
	public static final String BUYING = "buying";
	public static final String SELLING = "selling";
	public static final String ID = "id";

}

