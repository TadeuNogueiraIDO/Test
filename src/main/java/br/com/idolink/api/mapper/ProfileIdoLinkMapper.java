package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.ProfileIdoLinkRequest;
import br.com.idolink.api.dto.response.ProfileIdoLinkResponse;
import br.com.idolink.api.model.ProfileIdoLink;
@Component
public class ProfileIdoLinkMapper {
	
	@Autowired
	private ModelMapper mapper;	
	
		
	public ProfileIdoLinkResponse response(ProfileIdoLink model) {
		return mapper.map(model,ProfileIdoLinkResponse.class);		
	}
	
	public List<ProfileIdoLinkResponse> response(List<ProfileIdoLink> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());		
	}	
	
	public ProfileIdoLink requestToModel( ProfileIdoLinkRequest request) {
		return mapper.map(request,  ProfileIdoLink.class);
	}
	
	
	public ProfileIdoLinkResponse modelToResponse(ProfileIdoLink model) {
		ProfileIdoLinkResponse response = mapper.map(model,ProfileIdoLinkResponse.class);
		return response;
	}
	
}
