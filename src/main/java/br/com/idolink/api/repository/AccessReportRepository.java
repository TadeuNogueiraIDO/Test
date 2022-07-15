package br.com.idolink.api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.dto.response.ClicksToolsResponse;
import br.com.idolink.api.model.AccessReport;

@Repository
public interface AccessReportRepository extends JpaRepository<AccessReport, Long>{
	
	@Query("select count(*) from AccessReport a where a.ido.id in (:idoIds) and accessDate BETWEEN :startDate and :endDate")
	Long getTotalVisitors(List<Long> idoIds, LocalDate startDate, LocalDate endDate);
	
	@Query("select count(distinct a.ip) from AccessReport a where a.ido.id in (:idoIds) and accessDate BETWEEN :startDate and :endDate")
	Long getDistinctTotalVisitors(List<Long> idoIds, LocalDate startDate, LocalDate endDate);
	
	@Query("select count(*) from AccessReport a where a.ido.id in (:idoIds) and accessDate BETWEEN :startDate and :endDate and toolCodName IS NOT NULL")
	Long getTotalClicks(List<Long> idoIds, LocalDate startDate, LocalDate endDate);
	
	@Query("from AccessReport a where a.ido.id in (:idoIds) and accessDate BETWEEN :startDate and :endDate and toolCodName IS NOT NULL")
	List<AccessReport> findClicksTools(List<Long> idoIds, LocalDate startDate, LocalDate endDate);
	

	@Query("SELECT new br.com.idolink.api.dto.response.ClicksToolsResponse(count(a.ip), a.genericToolId,a.toolCodName) "
			+ "from AccessReport a where a.ido.id in (:idoIds) and accessDate BETWEEN :startDate and :endDate and toolCodName IS NOT NULL "
			+ "group by a.toolCodName, genericToolId ")
	List<ClicksToolsResponse> findClicksToolsGroupBy(List<Long> idoIds, LocalDate startDate, LocalDate endDate);
	

}

