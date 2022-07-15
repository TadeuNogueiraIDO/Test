package br.com.idolink.api.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.NewsletterFormRequest;
import br.com.idolink.api.dto.response.NewsletterFormResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.NewsletterFormMapper;
import br.com.idolink.api.model.ConfigNewsletterForm;
import br.com.idolink.api.model.NewsletterForm;
import br.com.idolink.api.model.Notification;
import br.com.idolink.api.model.NotificationSetting;
import br.com.idolink.api.model.User;
import br.com.idolink.api.model.enums.NotificationType;
import br.com.idolink.api.model.enums.StatusLeads;
import br.com.idolink.api.repository.ConfigNewsletterFormRepository;
import br.com.idolink.api.repository.NewsletterFormRepository;
import br.com.idolink.api.repository.NotificationSettingRepository;
import br.com.idolink.api.repository.UserRepository;
import br.com.idolink.api.service.MessagePropertieService;
import br.com.idolink.api.service.NewsletterFormService;
import br.com.idolink.api.service.NotificationService;

@Service
public class NewsletterFormServiceImpl implements NewsletterFormService {

	@Autowired
	private NewsletterFormMapper mapper;

	@Autowired
	private NewsletterFormRepository repository;
  
	@Autowired
	private ConfigNewsletterFormRepository configRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private NotificationSettingRepository notificationServiceRepository;

	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private MessagePropertieService messagePropertieService;

	@Override
	public NewsletterFormResponse findById(Long id) {
		Optional<NewsletterForm> model = repository.findById(id);
		validate(model, "NewsletterForm", "api.error.newsletter.form.not.found");
		return mapper.response(model.get());
	}

	@Override
	public List<NewsletterFormResponse> findByConfig(Long configId) {

		Optional<ConfigNewsletterForm> config = configRepository.findById(configId);

		if (config.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Config", "api.error.config.newsletter.not.found");
		}

		List<NewsletterForm> model = repository.findByConfigNewsletterForm(config.get());
		return mapper.response(model);
	}

	@Override
	@Transactional
	public NewsletterFormResponse create( Long configFormId, NewsletterFormRequest request) {

		NewsletterForm model = mapper.model(request);

		if (configFormId != null && configFormId != 0) {
			Optional<ConfigNewsletterForm> config = configRepository.findById(configFormId);
			validate(config, "Config", "api.error.config.newsletter.not.found");
			model.setConfigNewsletterForm(config.get());
		}
		model.setStatusLeads(StatusLeads.NOTREAD);
		model = repository.save(model);
		Optional<ConfigNewsletterForm> config = configRepository.findById(configFormId);
		Optional<User> user = userRepository.findByIdo(config.get().getIdo().getId());
		createNotification(user.get().getId());
		return mapper.response(model);
	}

	@Transactional
	public void delete(Long id) {

		Optional<NewsletterForm> model = repository.findById(id);
		validate(model, "Form", "api.error.newsletter.form.not.found");
	
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "NewsletterForm", "api.error.newsletter.form.conflict");
		}

	}

	private void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}

	private void createNotification(Long userId) {

		Optional<User> user = userRepository.findById(userId);

		NotificationSetting settingNotification = notificationServiceRepository.findBySettingUserId(user.get().getId());

		if (settingNotification.getRequests()) {

			Notification notification = Notification.builder().title(messagePropertieService.getMessageAttribute("api.notification.user.title.newsletter"))
					.message(messagePropertieService.getMessageAttribute("api.notification.user.form.newsletter")).icon("751a592c-2ff2-494f-ac6c-a071b10e8799").user(user.get()).read(false)
					.creationDate(LocalDate.now()).typeNotification(NotificationType.FORMSNEWSLETTER).build();

			notificationService.create(notification);
		}

	}
	
	
}
