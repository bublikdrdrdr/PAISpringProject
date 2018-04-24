package tk.ubublik.pai.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MarketCapRecord.class)
public abstract class MarketCapRecord_ {

	public static volatile SingularAttribute<MarketCapRecord, Date> date;
	public static volatile SingularAttribute<MarketCapRecord, Long> capitalization;
	public static volatile SingularAttribute<MarketCapRecord, Long> id;

	public static final String DATE = "date";
	public static final String CAPITALIZATION = "capitalization";
	public static final String ID = "id";

}

