package br.com.idolink.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.ToolConfigPackage;

@Repository
public interface ToolConfigPackageRepository extends ToolConfigPackageFilterRepository, JpaRepository<ToolConfigPackage, Long>{
	
	boolean existsById(Long id);
	
	
}
