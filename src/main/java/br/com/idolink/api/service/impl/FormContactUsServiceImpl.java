package br.com.idolink.api.service.impl;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.FormContactUsRequest;
import br.com.idolink.api.dto.response.FormContactUsResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.FormContactUsMapper;
import br.com.idolink.api.model.ConfigContactUs;
import br.com.idolink.api.model.FormContactUs;
import br.com.idolink.api.model.Notification;
import br.com.idolink.api.model.NotificationSetting;
import br.com.idolink.api.model.User;
import br.com.idolink.api.model.enums.NotificationType;
import br.com.idolink.api.model.enums.StatusLeads;
import br.com.idolink.api.repository.ConfigContactUsRepository;
import br.com.idolink.api.repository.FormContactUsRepository;
import br.com.idolink.api.repository.NotificationSettingRepository;
import br.com.idolink.api.repository.UserRepository;
import br.com.idolink.api.service.FormContactUsService;
import br.com.idolink.api.service.MessagePropertieService;
import br.com.idolink.api.service.NotificationService;
import br.com.idolink.api.service.email.SendEmailService;

@Service
public class FormContactUsServiceImpl implements FormContactUsService {

	@Autowired
	private FormContactUsMapper mapper;

	@Autowired
	private FormContactUsRepository repository;
	
	@Autowired
	private NotificationSettingRepository notificationServiceRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SendEmailService mail;
	
	@Autowired
	private ConfigContactUsRepository configRepository;
	
	@Autowired
	private MessagePropertieService messagePropertieService;
	
	@Autowired
	private NotificationService notificationService;

	@Override
	public FormContactUsResponse findById(Long id) {
		Optional<FormContactUs> model = repository.findById(id);
		validate(model, "FormContactUs", "api.error.form.contact.us.not.found");
		return mapper.response(model.get());
	}
	
	@Override
	@Transactional
	public FormContactUsResponse publicCreate(Long id, FormContactUsRequest request) {

		FormContactUs model = mapper.model(request);
			
		Optional<ConfigContactUs> config = repository.findByIdo(id);
		validate(config, "ConfigContactUs", "api.error.form.contact.us.not.found");
			
		model.setConfigContactUs(config.get());	
					
		model = repository.save(model);
		
		Optional<User> user = userRepository.findByIdo(id);
		createNotification(user.get().getId(), model.getId());
		
		String emailSubject = "Formulário fale conosco";
		String body = "";
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
			baos.write(new ClassPathResource("html/contact_us.html").getInputStream().readAllBytes());
			body = baos.toString();
			body = body.replace("{{contact-us-name}}", model.getName());
			body = body.replace("{{contact-us-email}}", model.getEmail());
			body = body.replace("{{contact-us-telephone}}", model.getTelephone());
			body = body.replace("{{contact-us-title}}", model.getTitle());
			body = body.replace("{{contact-us-text_box}}", model.getTextBox());
		} catch (Exception e) {
			e.printStackTrace();
		}
		mail.sendMail(config.get().getIdo().getBusiness().getUser().getEmail(), emailSubject, body);
		
		return mapper.response(model);
	}


	@Override
	@Transactional
	public FormContactUsResponse create(Long configContactId,  FormContactUsRequest request, Long userId) {

		FormContactUs model = mapper.model(request);
		
		if(configContactId!= null && configContactId != 0) {
			
			Optional<ConfigContactUs> config = configRepository.findById(configContactId);
			validate(config, "ConfigContactUs", "api.error.form.contact.us.not.found");
			model.setConfigContactUs(config.get());	
		}
		
		model.setStatusLeads(StatusLeads.NOTREAD);		
		
		model = repository.save(model);

		

		Optional<ConfigContactUs> config = configRepository.findById(configContactId);
		Optional<User> userNotification = userRepository.findByIdo(config.get().getIdo().getId());
		createNotification(userNotification.get().getId(), model.getId());


		
				
//		Optional<User> user = userRepository.findById(userId);
//		validate(user, "User", "api.error.user.not.found");
//		
//		String emailSubject = "Formulário fale conosco";
//		String body = "";
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		
//		try {
//			baos.write(new ClassPathResource("html/contact_us.html").getInputStream().readAllBytes());
//			body = baos.toString();
//			body = body.replace("{{contact-us-name}}", model.getName());
//			body = body.replace("{{contact-us-email}}", model.getEmail());
//			body = body.replace("{{contact-us-telephone}}", model.getTelephone());
//			body = body.replace("{{contact-us-title}}", model.getTitle());
//			body = body.replace("{{contact-us-text_box}}", model.getTextBox());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		mail.sendMail(user.get().getEmail(), emailSubject, body);
		
		return mapper.response(model);
	}

	@Transactional
	public void delete(Long id) {

		Optional<FormContactUs> model = repository.findById(id);
		validate(model, "FormContactUs", "api.error.form.contact.us.not.found");

		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "FormContactUs","api.error.form.contact.us.conflict");
		}

	}

	public void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
	
		private void createNotification(Long userId, Long id) {
		
        Optional<User> user = userRepository.findById(userId);
        
        NotificationSetting settingNotification = notificationServiceRepository.findBySettingUserId(user.get().getId());
	
		if(settingNotification.getRequests()) {
			
		Notification notification =	Notification.builder()
		.title(messagePropertieService.getMessageAttribute("api.notification.user.title.contact.us"))
		.message(messagePropertieService.getMessageAttribute("api.notification.user.form.contact"))
		.icon("5b0e5a6e-ccf8-498e-8ef6-1df03ded41bc")
		.user(user.get())
		.read(false)
		.creationDate(LocalDate.now())
		.typeNotification(NotificationType.FORMSCONTACT).build();
		
		
		notificationService.create(notification, id.toString());
		}
		
		
	}

}