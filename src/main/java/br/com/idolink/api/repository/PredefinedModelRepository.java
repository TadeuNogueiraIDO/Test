package br.com.idolink.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.PredefinedModel;
import br.com.idolink.api.model.enums.PredefinedModelsTemplate;

@Repository
public interface PredefinedModelRepository extends JpaRepository<PredefinedModel, Long> {
		
	@Query("FROM PredefinedModel i WHERE i.fileUiid is NOT NULL order by i.classification")
	List<PredefinedModel> findAllUiidNotNull();
		
	Optional<PredefinedModel> findByName(PredefinedModelsTemplate name);
	
	
}
