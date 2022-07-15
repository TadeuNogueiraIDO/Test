package br.com.idolink.api.service;

import br.com.idolink.api.dto.request.CustomDomainRequest;
import br.com.idolink.api.dto.response.CustomDomainResponse;

public interface CustomDomainService {

	CustomDomainResponse findById(Long id);
	
	CustomDomainResponse findByIdo(Long id);
	
	CustomDomainResponse create(CustomDomainRequest request, Long idoId);
	
	CustomDomainResponse update(CustomDomainRequest request, Long id);
	
	void delete(Long id);
}
