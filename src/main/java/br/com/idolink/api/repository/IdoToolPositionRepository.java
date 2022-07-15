package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.IdoToolPosition;
import br.com.idolink.api.model.enums.ToolCodName;

@Repository
public interface IdoToolPositionRepository extends JpaRepository<IdoToolPosition, Long> {

	@Query("SELECT MAX(position) AS lastPosition FROM IdoToolPosition i WHERE i.ido.id = :idoId")
	Long findLastPosition(@Param("idoId") Long idoId);
	
	@Query("FROM IdoToolPosition i WHERE i.ido.id = :idoId AND i.toolCodName = :toolCodName AND genericToolId = :genericId")
	IdoToolPosition findByIdoToolAndGenericId(Long idoId, ToolCodName toolCodName, Long genericId);
		
	
	@Query("FROM IdoToolPosition i WHERE i.ido.id = :idoId AND position > :maxPosition")
	List<IdoToolPosition> findByIdoByPosition(Long idoId, Long maxPosition);
	
	List<IdoToolPosition> findByIdoOrderByPosition(Ido ido);
	
	@Query("FROM IdoToolPosition i WHERE i.ido.id = :id order by position")
	List<IdoToolPosition> findByIdoIdOrderByPosition(Long id);
}
