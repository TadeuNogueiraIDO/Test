package br.com.idolink.api.service;

import br.com.idolink.api.dto.request.NotificationSettingRequest;
import br.com.idolink.api.dto.response.NotificationSettingResponse;
import br.com.idolink.api.model.NotificationSetting;

public interface NotificationSettingService {

	 NotificationSettingResponse listByNotificationStettingUserLogged(Long userId);
	 
	 void create(NotificationSetting notificationSetting);

	 NotificationSettingResponse update(Long id, NotificationSettingRequest request);
}
