package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.response.ColorsResponse;
import br.com.idolink.api.model.Colors;

@Component
public class ColorsMapper {

	@Autowired
	private ModelMapper mapper;

	public ColorsResponse response(Colors model) {
		return mapper.map(model, ColorsResponse.class);
	}

	public List<ColorsResponse> response(List<Colors> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}
}
