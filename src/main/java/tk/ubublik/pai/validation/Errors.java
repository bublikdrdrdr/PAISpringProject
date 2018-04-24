package tk.ubublik.pai.validation;

import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Set;

import java.util.HashSet;

public class Errors extends HashSet<String> {

	public static Errors emptyInstance(){
		return new Errors();
	}

}
