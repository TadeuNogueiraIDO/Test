package br.com.idolink.api.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.response.TextFontResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.TextFontMapper;
import br.com.idolink.api.model.TextFont;
import br.com.idolink.api.repository.TextFontRepository;
import br.com.idolink.api.service.TextFontService;

@Service
public class TextFontServiceImpl implements TextFontService {

	@Autowired
	private TextFontRepository repository;

	@Autowired
	private TextFontMapper mapper;

	@Override
	public List<TextFontResponse> list() {

		List<TextFont> model = repository.findAll();

		return mapper.response(model);
	}

	
	@Override
	public TextFont findByName(String name) {
		return findByName(name, false);
	}
	
	
	@Override
	public TextFont findByName(String name, boolean returnDefault) {
		
		TextFont font = repository.findByName(name);
		
		if(Objects.isNull(font)) {
			
			if(returnDefault) {
				font = TextFont.builder().id(1L).build();
			}else {
				throw new BaseFullException(HttpStatus.NOT_FOUND, "TextFont", "api.error.appearance.button.text.font.not.found");
			}
					
		}
			
		return font;
	}
}
