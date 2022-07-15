package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.ToolPlanPackage;
import br.com.idolink.api.model.enums.ToolPlanPackageType;

@Repository
public interface ToolPlanPackageRepository extends JpaRepository<ToolPlanPackage, Long>{

	Boolean existsByToolTypeAndActiveIsTrue(ToolPlanPackageType toolType);
	
	@Query("FROM ToolPlanPackage t WHERE t.active = true AND t.toolType = :toolType")
	ToolPlanPackage findByToolType(ToolPlanPackageType toolType);
	
	@Query("FROM ToolPlanPackage t WHERE t.active = true")
	@Override
	List<ToolPlanPackage> findAll();
	
}
