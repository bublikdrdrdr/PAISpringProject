package tk.ubublik.pai.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import tk.ubublik.pai.entity.*;

import javax.persistence.criteria.Predicate;
import java.util.Date;

public class BlockSpecifications {

	public static Specification<Block> dateSpecification(User user, Date date) {
		return (Specification<Block>) (root, query, criteriaBuilder) -> {
			Predicate userPredicate = criteriaBuilder.equal(root.get(Block_.USER), user);
			Predicate dateLessThanPredicate = criteriaBuilder.lessThanOrEqualTo(root.get(Block_.START), date);
			Predicate dateGreaterThanPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get(Block_.END), date);
			return criteriaBuilder.and(userPredicate, dateLessThanPredicate, dateGreaterThanPredicate);
		};
	}
}
