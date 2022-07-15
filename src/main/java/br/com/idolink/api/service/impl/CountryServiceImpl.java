package br.com.idolink.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.response.CountryResponse;
import br.com.idolink.api.mapper.CountryMapper;
import br.com.idolink.api.model.Country;
import br.com.idolink.api.repository.CountryRepository;
import br.com.idolink.api.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService{

	@Autowired
	private CountryRepository repository;
	
	@Autowired
	private CountryMapper mapper;
	
	
	@Override
	public Page<CountryResponse> list(Pageable pageable) {
 
		Page<Country> model = repository.findAll(pageable);

		return model.map(m -> mapper.response(m));
	}

	@Override
	public List<CountryResponse> listByBraEuaCad() {
 
		List<Country> model = repository.listByBraEuaCad();
		return mapper.response(model);
	}
	
	
}
