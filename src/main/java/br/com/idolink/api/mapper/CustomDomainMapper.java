package br.com.idolink.api.mapper;

import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.CustomDomainRequest;
import br.com.idolink.api.dto.response.CustomDomainResponse;
import br.com.idolink.api.model.CustomDomain;

@Component
public class CustomDomainMapper {

	public CustomDomainResponse modelToResponse(CustomDomain model) {
		CustomDomainResponse response = new CustomDomainResponse();
		response.setDomain(model.getDomain());
		return response;
	}
	
	public CustomDomain requestToModel(CustomDomainRequest request) {
		CustomDomain model = new CustomDomain();
		model.setDomain(request.getDomain());
		return model;
	}
}
