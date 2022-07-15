package br.com.idolink.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.FormContactUsRequest;
import br.com.idolink.api.dto.response.FormContactUsResponse;
import br.com.idolink.api.model.FormContactUs;

@Component
public class FormContactUsMapper {

	@Autowired
	private ModelMapper mapper;

	public FormContactUsResponse response(FormContactUs model) {
		return mapper.map(model, FormContactUsResponse.class);
	}

	public FormContactUs model(FormContactUsRequest request) {
		return mapper.map(request, FormContactUs.class);
	}
}
