package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.IdoTool;
import br.com.idolink.api.model.Tool;
import br.com.idolink.api.model.enums.IdoToolStatus;

@Repository
public interface IdoToolRepository extends JpaRepository<IdoTool, Long>, IdoToolFilterRepository{

	List<IdoTool> findByIdoAndTool(Ido ido, Tool tool);
	
	List<IdoTool> findToolByIdoAndStatus(Ido ido, IdoToolStatus status);
	
	@Query("FROM IdoTool i WHERE i.ido.id = :idoId AND i.tool.id = :toolId")
	IdoTool findByIdoAndTool(Long idoId, Long toolId);
	
	@Query("FROM IdoTool i WHERE i.ido.id = :idoId AND i.tool.isListable = true")
	List<IdoTool> findByIdo(Long idoId);
	
	@Query("FROM IdoTool i WHERE i.ido.id IN :idoId")
	List<IdoTool> findByIdos(List<Long> idoId);
}
