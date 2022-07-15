package br.com.idolink.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.idolink.api.dto.request.ActivityRequest;
import br.com.idolink.api.dto.response.ActivityFullResponse;
import br.com.idolink.api.dto.response.ActivityResponse;
import br.com.idolink.api.model.enums.TypeActivity;

public interface ActivityService {

	List<ActivityResponse> listSegment(TypeActivity typeActivity);

	ActivityResponse findById(Long id);

	ActivityResponse create(ActivityRequest request);

	ActivityResponse update(Long id, ActivityRequest request);

	void delete(Long id);

	Page<ActivityFullResponse> findAll(Pageable pageable);
	
	Page<ActivityFullResponse> findByFilterContainingIgnoreCase(String name, Pageable pageable);
}
