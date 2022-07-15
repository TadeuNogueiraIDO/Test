package br.com.idolink.api.service;

import br.com.idolink.api.dto.request.ProfileIdoLinkRequest;
import br.com.idolink.api.dto.response.ProfileIdoLinkResponse;
import br.com.idolink.api.model.enums.ProfileIdoCodName;


public interface ProfileIdoLinkService {
			
	 ProfileIdoLinkResponse findByCodName(ProfileIdoCodName codName);

	 ProfileIdoLinkResponse create(ProfileIdoLinkRequest request);
	 
	 ProfileIdoLinkResponse update(Long id, ProfileIdoLinkRequest request);
}
