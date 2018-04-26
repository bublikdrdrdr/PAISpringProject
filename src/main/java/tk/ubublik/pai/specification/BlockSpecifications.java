package tk.ubublik.pai.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import tk.ubublik.pai.entity.*;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlockSpecifications {

	public static Specification<Block> dateSpecification(User user, Date date) {
		return (Specification<Block>) (root, query, criteriaBuilder) -> {
			Predicate userPredicate = criteriaBuilder.equal(root.get(Block_.USER), user);
			Predicate dateLessThanPredicate = criteriaBuilder.lessThanOrEqualTo(root.get(Block_.START), date);
			Predicate dateGreaterThanPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get(Block_.END), date);
			return criteriaBuilder.and(userPredicate, dateLessThanPredicate, dateGreaterThanPredicate);
		};
	}

	public static Specification<Block> userAdminSpecification(@Nullable User user, @Nullable User admin, @Nullable Date activeDate){
		return ((root, query, criteriaBuilder) -> {
			Predicate userPredicate = criteriaBuilder.equal(root.get(Block_.USER), user);
			Predicate adminPredicate = criteriaBuilder.equal(root.get(Block_.ADMIN), admin);
			Predicate dateLessThanPredicate = criteriaBuilder.lessThanOrEqualTo(root.get(Block_.START), activeDate);
			Predicate dateGreaterThanPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get(Block_.END), activeDate);
			Predicate datePredicate = criteriaBuilder.and(dateLessThanPredicate, dateGreaterThanPredicate);
			List<Predicate> list = new ArrayList<>();
			if (user!=null) list.add(userPredicate);
			if (admin!=null) list.add(adminPredicate);
			if (activeDate!=null) list.add(datePredicate);
			return criteriaBuilder.and((Predicate[]) list.toArray());
		});
	}
}
