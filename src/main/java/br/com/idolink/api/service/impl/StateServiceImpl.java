package br.com.idolink.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.response.StateResponse;
import br.com.idolink.api.mapper.StateMapper;
import br.com.idolink.api.model.State;
import br.com.idolink.api.repository.StateRepository;
import br.com.idolink.api.service.StateService;

@Service 
public class StateServiceImpl implements StateService{

	@Autowired
	private StateRepository repository;
	
	@Autowired
	private StateMapper mapper;
	
	
	public Page<StateResponse> list(Pageable pageable) {
 
		Page<State> model = repository.findAll(pageable);

		return model.map(m -> mapper.response(m));
	}


}
