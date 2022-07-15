package br.com.idolink.api.service;

import br.com.idolink.api.dto.request.FormContactUsRequest;
import br.com.idolink.api.dto.response.FormContactUsResponse;

public interface FormContactUsService {

	FormContactUsResponse findById(Long id);

	FormContactUsResponse create(Long configContactId, FormContactUsRequest request, Long userId);
	
	FormContactUsResponse publicCreate(Long idoId, FormContactUsRequest request);
	
	void delete(Long id);

}