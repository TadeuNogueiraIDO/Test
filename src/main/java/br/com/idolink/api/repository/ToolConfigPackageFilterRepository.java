package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.ToolConfigPackage;
import br.com.idolink.api.model.enums.PlanPackagePaymentStatus;

@Repository
public interface ToolConfigPackageFilterRepository {
	
	List<ToolConfigPackage> findByIdo(Long idoId, PlanPackagePaymentStatus status);
	
}
