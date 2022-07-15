package br.com.idolink.api.service;

import br.com.idolink.api.dto.request.LogoBioRequest;
import br.com.idolink.api.dto.response.LogoBioResponse;

public interface LogoBioService {
	
	LogoBioResponse publicFindByIdo(Long idoId);
	
	LogoBioResponse findByIdo(Long idoid);

	LogoBioResponse updateByIdo(Long idoId, LogoBioRequest request);

	LogoBioResponse createByIdo(Long idoId, LogoBioRequest request);
	 	 
}
