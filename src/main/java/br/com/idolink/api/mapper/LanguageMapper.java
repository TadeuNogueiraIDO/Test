package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.LanguageRequest;
import br.com.idolink.api.dto.response.LanguageResponse;
import br.com.idolink.api.model.Language;

@Service
public class LanguageMapper {

	@Autowired
	private ModelMapper mapper;

	public LanguageResponse response(Language model) {
		return mapper.map(model, LanguageResponse.class);
	}

	public List<LanguageResponse> response(List<Language> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}
	
	public Language idoRequestToModel(LanguageRequest request) {
		return mapper.map(request, Language.class);
	}
}
