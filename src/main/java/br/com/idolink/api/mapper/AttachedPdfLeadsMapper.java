package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.AttachedPdfLeadsRequest;
import br.com.idolink.api.dto.response.AttachedPdfLeadsResponse;
import br.com.idolink.api.model.AttachedPdfLeads;

@Service
public class AttachedPdfLeadsMapper {
	
	@Autowired
	private ModelMapper mapper;

	public AttachedPdfLeadsResponse response(AttachedPdfLeads model) {
		return mapper.map(model, AttachedPdfLeadsResponse.class);		
	}

	public List<AttachedPdfLeadsResponse> modelToResponse(List<AttachedPdfLeads> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());		
	}
	
	public AttachedPdfLeads requestToModel(AttachedPdfLeadsRequest request) {
		return mapper.map(request, AttachedPdfLeads.class);
	}
				
}
