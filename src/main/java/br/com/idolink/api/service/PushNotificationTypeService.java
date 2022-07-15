package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.PushNotificationTypeRequest;
import br.com.idolink.api.dto.response.PushNotificationTypeResponse;

public interface PushNotificationTypeService {

	PushNotificationTypeResponse update(PushNotificationTypeRequest request, Long id);

	PushNotificationTypeResponse findById(Long id);

	PushNotificationTypeResponse create(PushNotificationTypeRequest request);

	List<PushNotificationTypeResponse> findAll();
	
	void delete(Long id);
}
