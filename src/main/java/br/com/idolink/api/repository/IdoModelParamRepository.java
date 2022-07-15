package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.IdoModelParam;
import br.com.idolink.api.model.ModelParam;

@Repository
public interface IdoModelParamRepository extends JpaRepository<IdoModelParam, Long> {
	
	List<IdoModelParam> findByIdoAndModelParam(Ido ido, ModelParam modelParam);
	
	List<IdoModelParam> findByIdo(Ido ido);
	
}
