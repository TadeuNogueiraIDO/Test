package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.response.TextFontResponse;
import br.com.idolink.api.model.TextFont;

public interface TextFontService {

	List<TextFontResponse> list();
	
	TextFont findByName(String name);

	TextFont findByName(String name, boolean returnDefault);
}
