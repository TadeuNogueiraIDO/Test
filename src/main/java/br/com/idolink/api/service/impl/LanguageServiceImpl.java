package br.com.idolink.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.response.LanguageResponse;
import br.com.idolink.api.mapper.LanguageMapper;
import br.com.idolink.api.model.Language;
import br.com.idolink.api.repository.LanguageRepository;
import br.com.idolink.api.service.LanguageService;

@Service
public class LanguageServiceImpl implements LanguageService{
	
	@Autowired
	private LanguageRepository repository;

	@Autowired
	private LanguageMapper mapper;

	@Override
	public List<LanguageResponse> list() {

		List<Language> model = repository.findAll();

		return mapper.response(model);
	}

}
