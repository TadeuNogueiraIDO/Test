package br.com.idolink.api.service;


import br.com.idolink.api.dto.request.PoliciesTermsUseRequest;
import br.com.idolink.api.dto.response.PoliciesTermsUseResponse;

public interface PoliciesTermsUseService {

	PoliciesTermsUseResponse update (PoliciesTermsUseRequest request, Long shopId);
	
	void deleteById(Long id);
	
	PoliciesTermsUseResponse findById(Long shopId);

}
