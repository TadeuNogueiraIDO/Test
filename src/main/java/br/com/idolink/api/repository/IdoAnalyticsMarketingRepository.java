package br.com.idolink.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.AnalyticsMarketing;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.IdoAnalyticsMarketing;

@Repository
public interface IdoAnalyticsMarketingRepository extends JpaRepository<IdoAnalyticsMarketing, Long> {

	List<IdoAnalyticsMarketing> findByIdo(Ido ido);
	
	Optional<IdoAnalyticsMarketing> findByIdoAndAnalyticsMarketing(Ido ido, AnalyticsMarketing analyticsMarketing);

}
