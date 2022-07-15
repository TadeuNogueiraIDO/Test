package br.com.idolink.api.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.ActivityRequest;
import br.com.idolink.api.dto.response.ActivityFullResponse;
import br.com.idolink.api.dto.response.ActivityResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.ActivityMapper;
import br.com.idolink.api.model.Activity;
import br.com.idolink.api.model.enums.TypeActivity;
import br.com.idolink.api.repository.ActivityRepository;
import br.com.idolink.api.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepository repository;

	@Autowired
	private ActivityMapper mapper;

	@Override
	public Page<ActivityFullResponse> findAll(Pageable pageable) {
		Page<Activity> model = repository.findAll(pageable);
		return model.map(m -> mapper.responseFull(m));
	}
	
	@Override
	public Page<ActivityFullResponse> findByFilterContainingIgnoreCase(String name, Pageable pageable) {
		
		if(Objects.isNull(name) || name.isEmpty()) {
			return findAll(pageable);
		}
		
		Page<Activity> model = repository.findByFilterContainingIgnoreCase(name, pageable);
		return model.map(m -> mapper.responseFull(m));
	}
	
	@Override
	public List<ActivityResponse> listSegment(TypeActivity typeActivit) {
		List<Activity> model = repository.findByTypeActivity(typeActivit);
		return mapper.response(model);
	}

	@Override
	public ActivityResponse findById(Long id) {
		Optional<Activity> model = repository.findById(id);
		validate(model, "Activity", "api.error.activity.not.found");
		return mapper.response(model.get());
	}

	@Override
	@Transactional
	public ActivityResponse create(ActivityRequest request) {
		Activity model = mapper.model(request);
		model = repository.save(model);
		return mapper.response(model);
	}

	@Transactional
	@Override
	public ActivityResponse update(Long id, ActivityRequest request) {

		Activity model = repository.findById(id).orElseThrow(
				() -> new BaseFullException(HttpStatus.NOT_FOUND, "Activity", "api.error.activity.not.found"));

		BeanUtils.copyProperties(request, model, "id", "dateModel");
		return mapper.response(repository.save(model));
	}

	@Transactional
	@Override
	public void delete(Long id) {
		Optional<Activity> model = repository.findById(id);
		validate(model, "Activity", "api.error.activity.not.found");
		try {
			repository.deleteById(id);
			repository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Activity", "api.error.activity.conflict");
		}

	}

	private void validate(Optional<?> model, String field, String message) {
		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
}
