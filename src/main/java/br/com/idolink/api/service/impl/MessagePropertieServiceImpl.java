package br.com.idolink.api.service.impl;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.config.security.IdolinkSecurity;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.model.User;
import br.com.idolink.api.model.enums.IdoCodLanguage;
import br.com.idolink.api.repository.UserRepository;
import br.com.idolink.api.service.MessagePropertieService;

@Service
public class MessagePropertieServiceImpl implements MessagePropertieService {

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private IdolinkSecurity idoLinkSecurity;
	
	@Autowired
	private UserRepository userRepository;
	
	public String getMessageAttribute(String propertie) {
		String languagem = Objects.nonNull(idoLinkSecurity) && Objects.nonNull(idoLinkSecurity.getLanguage()) ? IdoCodLanguage.getCod(idoLinkSecurity.getLanguage()) : "pt";
	
		String i = messageSource.getMessage(propertie, null, Locale.forLanguageTag(languagem));
		if( i == null) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Translate", "api.error.properties.not.found");
		}
		
		return messageSource.getMessage(propertie, null, Locale.forLanguageTag(languagem));
	}
	
	public String getMessageAttributeByUser(String propertie, Long idUser) {
		Optional<User> user = userRepository.findById(idUser);
		
		String languagem = Objects.nonNull(user) && Objects.nonNull(user.get().getSettings().getLanguage().getId()) ? IdoCodLanguage.getCod(user.get().getSettings().getLanguage().getId()) : "pt";
	
		String i = messageSource.getMessage(propertie, null, Locale.forLanguageTag(languagem));
		if( i == null) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Translate", "api.error.properties.not.found");
		}
		
		return messageSource.getMessage(propertie, null, Locale.forLanguageTag(languagem));
	}
		
}
