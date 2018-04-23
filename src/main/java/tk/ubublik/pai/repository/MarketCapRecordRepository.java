package tk.ubublik.pai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tk.ubublik.pai.entity.MarketCapRecord;

import java.util.Date;
import java.util.List;

public interface MarketCapRecordRepository extends JpaRepository<MarketCapRecord, Long> {

	@Query("select m from MarketCapRecord m where m.date >= :dateFrom and m.date <= :dateTo")
	List<MarketCapRecord> findAll(@Param("dateFrom") Date from, @Param("dateTo") Date to);
}
