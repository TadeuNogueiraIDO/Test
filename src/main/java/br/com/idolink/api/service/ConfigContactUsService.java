package br.com.idolink.api.service;

import br.com.idolink.api.dto.request.ConfigContactUsRequest;
import br.com.idolink.api.dto.response.ConfigContactUsResponse;

public interface ConfigContactUsService {

	ConfigContactUsResponse findByIdo(Long idoId);
	
	ConfigContactUsResponse save(Long idoId, ConfigContactUsRequest request);
	
	ConfigContactUsResponse update(Long id, ConfigContactUsRequest request);
	
	void delete(Long id);

	ConfigContactUsResponse findById(Long id);

	ConfigContactUsResponse findByIdo(Long idoId, boolean valid);
	
	ConfigContactUsResponse publicFindByIdo(Long idoId);
	
	ConfigContactUsResponse publicFindByIdo(Long idoId, boolean validation);


}

