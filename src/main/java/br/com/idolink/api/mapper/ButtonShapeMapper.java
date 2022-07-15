package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.response.ButtonShapeResponse;
import br.com.idolink.api.model.ButtonShape;

@Component
public class ButtonShapeMapper {

	@Autowired
	private ModelMapper mapper;

	public ButtonShapeResponse response(ButtonShape model) {
		return mapper.map(model, ButtonShapeResponse.class);
	}

	public List<ButtonShapeResponse> response(List<ButtonShape> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}
}
