package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.IdoModelParamRequest;
import br.com.idolink.api.dto.response.IdoModelParamResponse;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.IdoModelParam;
import br.com.idolink.api.model.ModelParam;


public interface IdoModelParamService {

	 List <IdoModelParamResponse> list();
    
	 IdoModelParamResponse findById(Long id);

	 IdoModelParamResponse create(IdoModelParamRequest request);

	 IdoModelParamResponse update(Long id, IdoModelParamRequest request);

     void delete(Long id);

	List<IdoModelParamResponse> findByIdo(Ido ido);

	List<IdoModelParam> findByIdoAndModelParam(Ido ido, ModelParam modelParam);
        
}