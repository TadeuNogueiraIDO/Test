package br.com.idolink.api.service.impl;

import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.NotificationSettingRequest;
import br.com.idolink.api.dto.response.NotificationSettingResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.NotificationSettingMapper;
import br.com.idolink.api.model.NotificationSetting;
import br.com.idolink.api.repository.NotificationSettingRepository;
import br.com.idolink.api.service.NotificationSettingService;

@Service
public class NotificationSettingServiceImpl implements NotificationSettingService{

	@Autowired
	private NotificationSettingRepository repository;

	@Autowired
	private NotificationSettingMapper mapper;
	
	@Override
	public NotificationSettingResponse listByNotificationStettingUserLogged(Long userId) {
		
		NotificationSetting notificationSetting = repository.findByUserId(userId);
		
		return mapper.response(notificationSetting);
	}
	
	@Override
	@Transactional
	public void create(NotificationSetting notificationSetting) {
	
		repository.save(notificationSetting);
	}
	
	@Override
	@Transactional
	public NotificationSettingResponse update(Long id, NotificationSettingRequest request) {

		NotificationSetting model = repository.findById(id).orElseThrow(
				() -> new BaseFullException(HttpStatus.NOT_FOUND, "NotificationSetting", "api.error.notification.setting.not.found"));

		BeanUtils.copyProperties(request, model, "id", "dateModel");
		return mapper.response(repository.save(model));
	}
	
	public void validateConfiguration(Long userId) {
		NotificationSetting user = repository.findBySettingUserId(userId);
		
		if(Objects.nonNull(user)) {
			throw new BaseFullException(HttpStatus.CONFLICT, "NotificationSetting","api.error.notification.setting.conflict" );
		}
	}
	
}