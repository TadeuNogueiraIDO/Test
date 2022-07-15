package br.com.idolink.api.service.impl;

import static java.util.Objects.nonNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.api.OneSignalApi;
import br.com.idolink.api.dto.request.NotificationApiRequest;
import br.com.idolink.api.dto.request.builder.NotificationApiRequestBuilder;
import br.com.idolink.api.dto.response.NotificationFilterResponse;
import br.com.idolink.api.dto.response.NotificationResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.NotificationMapper;
import br.com.idolink.api.model.Notification;
import br.com.idolink.api.model.User;
import br.com.idolink.api.model.enums.NotificationType;
import br.com.idolink.api.repository.NotificationRepository;
import br.com.idolink.api.repository.UserRepository;
import br.com.idolink.api.service.NotificationService;
import br.com.idolink.api.utils.IdoStringUtils;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationRepository repository;

	@Autowired
	private NotificationMapper mapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OneSignalApi oneSignalApi;

	@Override
	public List<NotificationResponse> findAllbyIdType(NotificationType typeNotification, Long idUser) {

		Optional<User> user = userRepository.findById(idUser);

		List<Notification> model = repository.findByUserIdByDateCreationByType(user.get().getId(), typeNotification.toString());

		List<NotificationResponse> response = mapper.response(model);

		return response;

	}

	@Override
	public List<NotificationResponse> listByUserLogged(Long userId) {

		List<Notification> model = repository.findByUserIdByDateCreation(userId);

		List<NotificationResponse> response = mapper.response(model);

		return response;

	}

	@Override
	public List<NotificationFilterResponse> findByUserByDate(Long userId) {

		List<NotificationFilterResponse> filter = new ArrayList<>();

		List<Timestamp> model = repository.findByDatebyUser(userId);

		for (Timestamp date : model) {

			List<Notification> not = repository.findByNotificationByDate(date, userId);

			List<NotificationResponse> response = mapper.response(not);

			NotificationFilterResponse save = NotificationFilterResponse.builder().creationDate(date)
					.notification(response).build();

			filter.add(save);

		}

		return filter;

	}
	
	@Transactional
	@Override
	public void create(Notification notification) {

		repository.save(notification);
		
		HashMap<String, String> aditionalValues = new HashMap<>();
		aditionalValues.put("type_enum", notification.getTypeNotification().toString());

		NotificationApiRequest req = NotificationApiRequestBuilder.init().content(notification.getMessage(), "")
				.heading(notification.getTitle())
				.uuids(Arrays.asList(userRepository.findByUuidUser(notification.getUser().getId())))
				.additionalFields(aditionalValues)
				.push()
				.build();

		oneSignalApi.send(req);

	}

	@Transactional
	@Override
	public void create(Notification notification, String id) {

		repository.save(notification);
		
		HashMap<String, String> aditionalValues = new HashMap<>();
		aditionalValues.put("type_enum", notification.getTypeNotification().toString());
		
		if(nonNull(id)) {
			aditionalValues.put("id", id);
		}
		NotificationApiRequest req = NotificationApiRequestBuilder.init().content(notification.getMessage(), "")
				.heading(notification.getTitle())
				.uuids(Arrays.asList(userRepository.findByUuidUser(notification.getUser().getId())))
				.additionalFields(aditionalValues)
				.push()
				.build();

		oneSignalApi.send(req);

	}

	@Override
	public List<NotificationResponse> list() {

		List<Notification> model = repository.findAll();

		List<NotificationResponse> response = mapper.response(model);

		return response;
	}

	@Transactional
	@Override
	public void delete(Long id) {

		Optional<Notification> model = repository.findById(id);

		if (model.isEmpty()) {

			throw new BaseFullException(HttpStatus.NOT_FOUND, "Notification", "api.error.notification.not.found");

		}

		repository.deleteById(id);

	}

	@Transactional
	@Override
	public List<NotificationResponse> update(Long idUser, Boolean status, String idNotifications) {

		String formatString = idNotifications;

		List<NotificationResponse> response = new ArrayList<>();

		List<Notification> not = repository.findByAllNotificationByUser(idUser);

		response = mapper.response(not);

		List<Notification> updated = new ArrayList<>();

		List<Long> idsNotificatios = IdoStringUtils.convertStringLong(formatString);
		
		for (int z = 0; z < idsNotificatios.size(); z++) {

			int i = 0;
			for (NotificationResponse valid : response) {

				if (valid.getId().equals(idsNotificatios.get(z))) {
					i = 1;
				}

			}
			if (i == 0) {

				throw new BaseFullException(HttpStatus.NOT_FOUND, "Notification", "api.error.notification.not.found");

			}
			Optional<Notification> notification = repository.findById(idsNotificatios.get(z));

			notification.get().setRead(status);

			updated.add(notification.get());

			repository.save(notification.get());

		}

		List<NotificationResponse> responseSave = mapper.response(updated);

		return responseSave;

	}

	@Transactional
	@Override
	public void deleteAll(Long userId) {

		Optional<User> user = userRepository.findById(userId);
		validateNotification(user, "Notification", "api.error.notification.user.not.found");

		repository.deleteByUser(user.get());

	}

	private void validateNotification(Optional<User> user, String field, String message) {

		List<Notification> model = repository.findByUserIdByDateCreation(user.get().getId());

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}

	}

}
