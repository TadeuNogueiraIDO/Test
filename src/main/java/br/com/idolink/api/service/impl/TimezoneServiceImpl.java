package br.com.idolink.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.response.TimezoneResponse;
import br.com.idolink.api.mapper.TimezoneMapper;
import br.com.idolink.api.model.Timezone;
import br.com.idolink.api.repository.TimezoneRepository;
import br.com.idolink.api.service.TimezoneService;

@Service
public class TimezoneServiceImpl implements TimezoneService {
	
	@Autowired
	private TimezoneRepository repository;
	
	@Autowired
	private TimezoneMapper mapper;
	
	@Override
	public List<TimezoneResponse> list() {

		List<Timezone> model = repository.findAll();

		return mapper.response(model);
	}
	
}
