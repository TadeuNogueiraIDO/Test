package br.com.idolink.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.ButtonAnimationRequest;
import br.com.idolink.api.dto.response.ButtonAnimationResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.ButtonAnimationMapper;
import br.com.idolink.api.model.ButtonAnimation;
import br.com.idolink.api.repository.ButtonAnimationRepository;
import br.com.idolink.api.service.ButtonAnimationService;

@Service
public class ButtonAnimationServiceImpl implements ButtonAnimationService {

	@Autowired
	private ButtonAnimationRepository repository;
	
	@Autowired
	private ButtonAnimationMapper mapper;
	
	@Override
	public ButtonAnimationResponse findById(Long id) {
		Optional<ButtonAnimation> model = repository.findById(id);
		validate(model, "ButtonAnimation", "api.error.button.animation.not.found");
		return mapper.response(model.get());
	}
	
	@Override
	public List<ButtonAnimation> findListById(List<ButtonAnimationRequest> buttonList) {
		
		List<ButtonAnimation> list = new ArrayList<>();
		
		if(Objects.nonNull(buttonList)) {
			Iterable<Long> ids = buttonList.stream().map(ButtonAnimationRequest::getId).collect(Collectors.toList());
			list = repository.findAllById(ids);
		}
		return list;
	}

	@Override
	@Transactional
	public ButtonAnimationResponse create(ButtonAnimationRequest request) {
		ButtonAnimation model = mapper.model(request);
		repository.save(model);
				
		return mapper.response(model);
	}

	@Override
	@Transactional
	public ButtonAnimationResponse update(Long id, ButtonAnimationRequest request) {

		@SuppressWarnings("serial")
		ButtonAnimation model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "ButtonAnimation","api.error.button.animation.not.found") {
				});
		BeanUtils.copyProperties(request, model, "id", "dateModel");
		repository.save(model);
		return mapper.response(model);

	}

	@Transactional
	public void delete(Long id) {

		Optional<ButtonAnimation> model = repository.findById(id);
		validate(model,"ButtonAnimation","api.error.button.animation.not.found");

		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "ButtonAnimation", "api.error.button.animation.conflict");
		}

	}
			
	private void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}

}
