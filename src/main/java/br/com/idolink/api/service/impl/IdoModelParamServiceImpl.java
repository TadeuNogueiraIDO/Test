package br.com.idolink.api.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.IdoModelParamRequest;
import br.com.idolink.api.dto.response.IdoModelParamResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.IdoModelParamMapper;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.IdoModelParam;
import br.com.idolink.api.model.ModelParam;
import br.com.idolink.api.repository.IdoModelParamRepository;
import br.com.idolink.api.service.IdoModelParamService;

@Service
public class IdoModelParamServiceImpl implements IdoModelParamService {

	@Autowired
	private IdoModelParamRepository repository;

	@Autowired
	private IdoModelParamMapper mapper;
	
	@Override
	public List<IdoModelParamResponse> list() {

		List<IdoModelParam> model = repository.findAll();

		return mapper.response(model);
	}

	@Override
	public IdoModelParamResponse findById(Long id) {
		Optional<IdoModelParam> model = repository.findById(id);
		validate(model, "IdoModelParam", "api.error.ido.model.param.not.found");

		return mapper.response(model.get());
	}
	
	
	@Override
	public List<IdoModelParam> findByIdoAndModelParam(Ido ido, ModelParam modelParam) {
		List<IdoModelParam> models = repository.findByIdoAndModelParam(ido, modelParam);
		return models;
	}
	
	

	@Override
	@Transactional
	public IdoModelParamResponse create(IdoModelParamRequest request) {
		IdoModelParam model = mapper.model(request);
		return mapper.response(repository.save(model));
	}

	@Override
	@Transactional
	public IdoModelParamResponse update(Long id, IdoModelParamRequest request) {

		@SuppressWarnings("serial")
		IdoModelParam model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,"IdoModelParam", "api.error.ido.model.param.not.found") {
				});

		BeanUtils.copyProperties(request, model, "id","dateModel");
		return mapper.response(repository.save(model));

	}

	@Transactional
	public void delete(Long id) {

		Optional<IdoModelParam> model = repository.findById(id);
		validate(model,"IdoModelParam", "api.error.ido.model.param.not.found");

		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "IdoModelParam", "api.error.ido.model.param.not.found");
		}

	}
	
	@Override
	public List<IdoModelParamResponse> findByIdo(Ido ido){
		List<IdoModelParam> list = repository.findByIdo(ido);
		return mapper.response(list);	
	}
	
	private void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
	
	
	
	
}
