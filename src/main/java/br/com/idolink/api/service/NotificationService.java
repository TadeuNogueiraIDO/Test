package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.response.NotificationFilterResponse;
import br.com.idolink.api.dto.response.NotificationResponse;
import br.com.idolink.api.model.Notification;
import br.com.idolink.api.model.enums.NotificationType;


public interface NotificationService {

	List<NotificationResponse> list();

	List<NotificationResponse> listByUserLogged(Long userId);

	void delete(Long id);

	void deleteAll(Long userid);

	void create(Notification model, String id);
	
	void create(Notification model);

	List<NotificationResponse> findAllbyIdType(NotificationType typeNotification, Long idUser);

	List<NotificationFilterResponse> findByUserByDate(Long userId);

	List<NotificationResponse> update(Long idUser, Boolean status, String idNotifications); 
}
