package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.response.LanguageResponse;

public interface LanguageService {

	List<LanguageResponse> list();
}
