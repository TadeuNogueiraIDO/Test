package br.com.idolink.api.service.impl;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.GeneralSettingsRequest;
import br.com.idolink.api.dto.request.LanguageRequest;
import br.com.idolink.api.dto.request.PasswordRecoveryRequest;
import br.com.idolink.api.dto.request.PersonalDataUserRequest;
import br.com.idolink.api.dto.request.UserPasswordRequest;
import br.com.idolink.api.dto.request.UserRequest;
import br.com.idolink.api.dto.request.UserStatusRequest;
import br.com.idolink.api.dto.response.PersonalDataUserResponse;
import br.com.idolink.api.dto.response.UserResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.UserMapper;
import br.com.idolink.api.model.Country;
import br.com.idolink.api.model.NotificationSetting;
import br.com.idolink.api.model.User;
import br.com.idolink.api.model.UserPasswordRecovery;
import br.com.idolink.api.model.UserValidateAccount;
import br.com.idolink.api.repository.CountryRepository;
import br.com.idolink.api.repository.UserPasswordRecoveryRepository;
import br.com.idolink.api.repository.UserRepository;
import br.com.idolink.api.repository.UserValidateAccountRepository;
import br.com.idolink.api.service.GeneralSettingsService;
import br.com.idolink.api.service.InvoiceWalletService;
import br.com.idolink.api.service.NotificationSettingService;
import br.com.idolink.api.service.UserService;
import br.com.idolink.api.service.email.SendEmailService;
import br.com.idolink.api.utils.Validator;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserMapper mapper;

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private SendEmailService sendingEmail;
	
	@Autowired
	private InvoiceWalletService invoiceService;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private NotificationSettingService notificationSettingsService;
	
	@Autowired
	private GeneralSettingsService generalSettingsService;
	
	@Autowired
	private UserPasswordRecoveryRepository recoveryRepository;
	
	@Autowired
	private UserValidateAccountRepository validateAccountRepository;
	
	@Value("${api.host}")
	private String host;
	
	@Override
	public Page<UserResponse> list(Pageable pageable) {

		Page<User> model = repository.findAll(pageable);

		return model.map(m -> mapper.response(m));
	}

//	@Override
//	public User find(Long id) {
//		Optional<User> model = repository.findById(id);
//		Validator.validate(model, "User","api.error.user.not.found");
//		return model.get();
//	}
	
	@Override
	public UserResponse findById(Long id) {
		Optional<User> model = repository.findById(id);
		Validator.validate(model, "User","api.error.user.not.found");

		return mapper.response(model.get());
	}

	@Override
	@Transactional
	public UserResponse create(UserRequest request) {

		validateExistEmail(request.getEmail());
		
		Optional<Country> country = countryRepository.findById(request.getIdCountry());
	    
		
		Country countryResp = new Country();
		
		
		if(country.get().getId() != null) {
			Optional<Country> c  = countryRepository.findById(country.get().getId());
			
			if(!c.isEmpty()) {
				countryResp = c.get();
			}
		}
		
		User model = mapper.model(request);
		
		model.setValidateEmail(false);
		
		model.setCountry(countryResp);
		model.setPassword(encoder.encode(request.getPassword()));

		model = repository.save(model);
		
		validateAccountByEmail(model.getId());
		
		createBaseConfigurationNotificationSettings(model);
		
		GeneralSettingsRequest requestSettings = new GeneralSettingsRequest();
		
		LanguageRequest languageRequest = new LanguageRequest();
		
		UserStatusRequest usersatusRequest = new UserStatusRequest();
		
		languageRequest.setId(3L);
		
		usersatusRequest.setStatus(true);
		
		requestSettings.setLanguage(languageRequest);
		requestSettings.setSensitiveContent(true);
		requestSettings.setUser(request);
		requestSettings.setStatus(usersatusRequest);
	
	    generalSettingsService.createSave(requestSettings, model);
		
		invoiceService.createInvoice(model.getId()); 
		return mapper.response(model);

	}
	
	@Override
	@Transactional
	public UserResponse updateCountry(Long userId, Long idCountry) {
		
		Optional<User> user = repository.findById(userId);

		Optional<Country> country = countryRepository.findById(idCountry);
	
		user.get().setCountry(country.get());
		
		return mapper.response(repository.save(user.get()));		
		
	}
	@Override
	@Transactional
	public UserResponse update(Long id, UserRequest request) {

		@SuppressWarnings("serial")
		User model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "User","api.error.user.not.found") {
				});

		BeanUtils.copyProperties(request, model, "id","dateModel");
		model.setPassword(encoder.encode(request.getPassword()));
		return mapper.response(repository.save(model));

	}

	@Transactional
	public void delete(Long id) {

		Optional<User> model = repository.findById(id);
		Validator.validate(model, "User","api.error.user.not.found");

		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "User", "api.error.user.conflict");
		}

	}

	
	
	@Override
	public UserResponse updatePassword(UserPasswordRequest password, Long id) {

		Optional<User> model = repository.findById(id);
		Validator.validate(model, "User","api.error.user.not.found");
		
		if(!password.getNewPassword().equals(password.getConfirmNewPassword())) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Password", "api.error.user.password.conflict");		
		}
		
		validatePassword(model.get().getPassword(), password.getCurrentPassword());
		model.get().setPassword(encoder.encode(password.getNewPassword()));
		repository.save(model.get());
		return mapper.modelToResponse(model.get());

	}
	
	
	@Override
	@Transactional
	public UserResponse updateEmailUser(String email, Long id) {
		
		Optional<User> model = repository.findById(id);
		Validator.validate(model, "User","api.error.user.not.found");
		
		User user = model.get();
		user.setEmail(email);		
		repository.save(user);
		return mapper.modelToResponse(user);
	}
	
	@Override
	@Transactional
	public UserResponse updateAvatarUser(Long avatar, Long id) {
		
		Optional<User> model = repository.findById(id);
		Validator.validate(model, "User","api.error.user.not.found");
		
		User user = model.get();
		user.setFileAvatar(avatar);		
		repository.save(user);
		return  mapper.response(user);
	}
	
	@Override
	@Transactional
	public PersonalDataUserResponse updatePersonalData(Long id, PersonalDataUserRequest request) {

		User model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "User","api.error.user.not.found"));

		model.setName(request.getName());
		model.setBirthData(request.getBirthData());
		model.setDialCode(request.getDialCode());
		model.setNumber(request.getNumber());

		model = repository.save(model);

		return mapper.modelResponse(model);
	}
	
	@Override
	@Transactional
	public String emailAutorization(String token) {
		
		String body = "";
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		
		UserValidateAccount model = validateAccountRepository.findByCode(token);

		try {
			baos.write(new ClassPathResource("html/success-email-validated.html").getInputStream().readAllBytes());
			body = baos.toString();
			body = body.replace("{{insert-name}}", model.getUser().getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(model.getUser().getEmail() == null) {
			
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "User","api.error.user.email.not.found");
			
		} 
		else {
		
			model.getUser().setValidateEmail(true);
			
		}
		
		repository.save(model.getUser());
		
		return body;
		
}
	
	
	public String generateNewPassword() {
        int maxCharacters = 10;
        String[] characters = { "a", "1", "b", "2", "4", "5", "6", "7", "8",
                "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
                "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I",
                "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
                "V", "W", "X", "Y", "Z", "+","-","/","*","_","!","@","$","%","&" };
        StringBuilder senha = new StringBuilder();

        for (int i = 0; i < maxCharacters; i++) {
            int posicao = (int) (Math.random() * characters.length);
            senha.append(characters[posicao]);
        }
        
        return senha.toString();
    }


	private void validateExistEmail(String email) {
		Optional<User> model = repository.findByEmail(email);
		if (model.isPresent()) {
			throw new BaseFullException(HttpStatus.CONFLICT, "Email", "api.error.email.conflict");
		}
	}
	
	private void validatePassword(String currentPassword, String password) {

        Boolean validPass = encoder.matches(password, currentPassword);

        if (!validPass) {
        	throw new BaseFullException(HttpStatus.BAD_REQUEST, "Password", "api.error.user.password.invalid.conflict");
        }
    }
	
	@Override
	@Transactional
	public void generatePasswordRecovery(String email) {
		User user = findByEmail(email);
		user.setEmail(email);
		String recoveryCode = UUID.randomUUID().toString();
		
		
		List<UserPasswordRecovery> oldCodes = recoveryRepository.findUnusedCode(user.getId());
		oldCodes.forEach(c -> c.setUsed(true));
		recoveryRepository.saveAll(oldCodes);
		
		UserPasswordRecovery recovery = UserPasswordRecovery.builder()
				.code(recoveryCode)
				.user(user)
				.used(false)
				.expirationDate(LocalDateTime.now().plusDays(1))
				.build();
		recoveryRepository.save(recovery);
		
		HashMap<String, String> values = new HashMap<>();
		values.put("{{password-reset-token}}", recoveryCode);

 		sendingEmail.sendEmail(email, "Alteração de senha", "password-reset.html", values);
	}
	
	@Override
	@Transactional
	public void validateAccountByEmail(Long idUser) {
		
		Optional<User> user = repository.findById(idUser);
		System.out.println(user.get());
		if(user.get().getEmail() == null) {
			
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "User","api.error.user.email.not.found");
		}
		
		String token = UUID.randomUUID().toString();
		
		List<UserValidateAccount> expiration = validateAccountRepository.findUnusedCode(user.get().getId());
		expiration.forEach(c -> c.setUsed(true));
		validateAccountRepository.saveAll(expiration);
		
		String url = host+"/user/email/authorization?token=";
		
		UserValidateAccount validateAccount = UserValidateAccount.builder()
				.user(user.get())
				.used(false)
				.code(token)
				.expirationDate(LocalDateTime.now().plusDays(1))
				.build();
		validateAccountRepository.save(validateAccount);

		//nesse campo entre aspas pode passar a url da mensagem que será traduzida de acordo com o idioma do usuário
		//String emailSubject = messagePropertieService.getMessageAttribute("Validação de Conta");

		HashMap<String, String> values = new HashMap<>();
		values.put("{{url-ambiente}}", url);
		values.put("{{validate-token}}", token);
		values.put("{{insert-name}}", user.get().getName());

 		sendingEmail.sendEmail(user.get().getEmail(), "Validação de Conta", "validate-account.html", values);
	}
	
	@Override
	@Transactional
	public void recoverPasswordWithCode(PasswordRecoveryRequest request) {
		UserPasswordRecovery recovery = recoveryRepository.findByCode(request.getCode());
		if(Objects.isNull(recovery)) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "User", "api.error.user.cod.not.found");
		}
		validateRecoveryToken(recovery);
		recovery.setUsed(true);
		recoveryRepository.save(recovery);
		
		Optional<User> user = repository.findById(recovery.getUser().getId());
		Validator.validate(user, "User","api.error.user.not.found");
		user.get().setPassword(encoder.encode(request.getPassword()));
		repository.save(user.get());
	}

	
	public User findByEmail(String email) {
		Optional<User> user = repository.findByEmail(email);
		Validator.validate(user, "User","api.error.user.email.not.found");
		return user.get();
	}
	
	private void validateRecoveryToken(UserPasswordRecovery recovery) {
		if(recovery.getUsed() != null && recovery.getUsed().equals(true)) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "User","api.error.user.link.conflict");
		}
		if(LocalDateTime.now().isAfter(recovery.getExpirationDate())) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "User","api.error.user.link.expired");
		}
		
	} 
	private void createBaseConfigurationNotificationSettings(User user) {
		
		NotificationSetting notificationSetting = NotificationSetting.builder()
				.activeNotifications(true)
				.commissions(true)
				.form(true)
				.wallet(true)
				.requests(true)
				.systemWarnings(true)
				.user(user).build();
		
		notificationSettingsService.create(notificationSetting);
	}

}
