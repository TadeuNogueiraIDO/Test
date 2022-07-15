package br.com.idolink.api.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.GeneralSettingsRequest;
import br.com.idolink.api.dto.request.LanguageRequest;
import br.com.idolink.api.dto.request.UserStatusRequest;
import br.com.idolink.api.dto.response.GeneralSettingsResponse;
import br.com.idolink.api.dto.response.LanguageResponse;
import br.com.idolink.api.dto.response.UserStatusResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.GeneralSettingsMapper;
import br.com.idolink.api.mapper.LanguageMapper;
import br.com.idolink.api.model.GeneralSettings;
import br.com.idolink.api.model.Language;
import br.com.idolink.api.model.User;
import br.com.idolink.api.repository.GeneralSettingsRepository;
import br.com.idolink.api.repository.LanguageRepository;
import br.com.idolink.api.repository.UserRepository;
import br.com.idolink.api.service.GeneralSettingsService;

@Service
public class GeneralSettingsServiceImpl implements GeneralSettingsService {

	@Autowired
	private GeneralSettingsRepository repository;

	@Autowired
	private LanguageRepository languageRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GeneralSettingsMapper mapper;

	@Autowired
	private LanguageMapper languageMapper;

	@Override
	@Transactional
	public GeneralSettingsResponse findByUser(Long userId) {

		Optional<User> user = userRepository.findById(userId);
		validate(user, "User", "api.error.user.not.found");

		Optional<GeneralSettings> generalSettings = repository.findByUser(user.get());
		GeneralSettings defaultSettings = new GeneralSettings();

		if (generalSettings.isEmpty()) {
			LanguageRequest languageRequest = LanguageRequest.builder().id(3L).build();
			UserStatusRequest userStatusRequest = UserStatusRequest.builder().status(true).build();

			GeneralSettingsRequest request = GeneralSettingsRequest.builder().language(languageRequest)
					.sensitiveContent(false).status(userStatusRequest).build();

			defaultSettings = this.create(request, user.get());

		}else {
			defaultSettings = generalSettings.get();
		}

		UserStatusResponse userStatusResponse = UserStatusResponse.builder().status(user.get().getStatus()).build();

		GeneralSettingsResponse response = mapper.response(defaultSettings);
		response.setStatus(userStatusResponse);

		return response;
	}

	@Override
	public List<LanguageResponse> listLanguages() {

		List<Language> model = languageRepository.findAll();

		return languageMapper.response(model);
	}

	@Override
	@Transactional
	public GeneralSettings create(GeneralSettingsRequest request, User user) {

		GeneralSettings model = mapper.model(request);

		if (Objects.isNull(request.getLanguage().getId())) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Language", "api.error.general.settings.language.null");
		}
		Optional<Language> language = languageRepository.findById(request.getLanguage().getId());
		validate(language, "Language", "api.error.general.settings.language.not.found ");
		model.setLanguage(language.get());
		model.setUser(user);
		model = repository.save(model);

		return model;

	}
	
	@Transactional
	public void createSave(GeneralSettingsRequest request, User user) {

		GeneralSettings model = mapper.model(request);

		if (Objects.isNull(request.getLanguage().getId())) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Language", "api.error.general.settings.language.null");
		}
		Optional<Language> language = languageRepository.findById(request.getLanguage().getId());
		validate(language, "Language", "api.error.general.settings.language.not.found ");
		model.setLanguage(language.get());
		model.setUser(user);
		model = repository.save(model);

		

	}

	@Override
	@Transactional
	public GeneralSettingsResponse update(Long id, GeneralSettingsRequest request) {

		GeneralSettings model = repository.findByUserId(id);
		if(Objects.isNull(model)){
			throw new BaseFullException(HttpStatus.NOT_FOUND,"GeneralSettings","api.error.general.settings.not.found");
		}
		
		GeneralSettings generalSettings = mapper.model(request);
		if (Objects.isNull(request.getLanguage().getId())) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Language", "api.error.general.settings.language.null");

		}
		
		Optional<Language> language = languageRepository.findById(request.getLanguage().getId());
		validate(language, "Language", "api.error.general.settings.language.not.found ");

		generalSettings.setLanguage(language.get());

		BeanUtils.copyProperties(generalSettings, model, "id", "dateModel", "user");
		model = repository.save(model);

		return mapper.response(model);
	}

	
	@Transactional
	public void delete(Long id) {

		Optional<GeneralSettings> model = repository.findById(id);
		validate(model, "User", "api.error.user.not.found");

		try {
			userRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {

			throw new BaseFullException(HttpStatus.BAD_REQUEST, "GeneralSettings","api.error.user.not.found");

		}

	}

	@Override
	@Transactional
	public UserStatusResponse updateStatus(Long id, UserStatusRequest request) {

		User model = userRepository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.BAD_REQUEST, "User", "api.error.user.inative"));

		model.setStatus(request.getStatus());

		model = userRepository.save(model);

		return mapper.modelToResponse(model);
	}

	private void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}

}
