package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.response.TextFontResponse;
import br.com.idolink.api.model.TextFont;

@Component
public class TextFontMapper {

	@Autowired
	private ModelMapper mapper;

	public TextFontResponse response(TextFont model) {
		return mapper.map(model, TextFontResponse.class);
	}

	public List<TextFontResponse> response(List<TextFont> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}
}
