package tk.ubublik.pai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tk.ubublik.pai.entity.Rate;

import java.util.Date;
import java.util.List;

public interface RateRepository extends JpaRepository<Rate, Long> {

	@Query("select r from Rate r where r.date >= :dateFrom and r.date <= :dateTo")
	List<Rate> findAll(@Param("dateFrom") Date from, @Param("dateTo") Date to);
}
