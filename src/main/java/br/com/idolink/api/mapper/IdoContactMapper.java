package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.response.IdoContactDialCodeResponse;
import br.com.idolink.api.dto.response.IdoContactResponse;
import br.com.idolink.api.dto.response.IdoContactValueResponse;
import br.com.idolink.api.model.IdoContact;

@Component
public class IdoContactMapper {


	@Autowired
	private ModelMapper mapper;


	public IdoContactResponse modelToResponse(IdoContact model) {
		return  mapper.map(model, IdoContactResponse.class);
	}

	public IdoContactValueResponse valueResponse(IdoContact model) {
		return mapper.map(model, IdoContactValueResponse.class);
	}

	public List<IdoContactResponse> modelToResponse(List<IdoContact> model) {

		return model.stream().map(m -> modelToResponse(m)).collect(Collectors.toList());
	}

	public IdoContactDialCodeResponse valueResponseDialCode(IdoContact model) {
		return mapper.map(model, IdoContactDialCodeResponse.class);
	}

}
