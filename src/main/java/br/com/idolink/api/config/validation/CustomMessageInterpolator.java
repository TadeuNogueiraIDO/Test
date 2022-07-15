package br.com.idolink.api.config.validation;

import java.util.Locale;
import java.util.Objects;

import javax.validation.MessageInterpolator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.validation.MessageInterpolatorFactory;
import org.springframework.context.MessageSource;

import br.com.idolink.api.config.security.IdolinkSecurity;
import br.com.idolink.api.model.enums.IdoCodLanguage;

public class CustomMessageInterpolator extends MessageInterpolatorFactory implements MessageInterpolator {

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private IdolinkSecurity idoLinkSecurity;

	@Override
	public String interpolate(String messageTemplate, Context context) {
		return getObject().interpolate(messageTemplate, context);
	}

	@Override
	public String interpolate(String messageTemplate, Context context, Locale locale) {
		if(!messageTemplate.contains("javax")) {
			String languagem = Objects.nonNull(idoLinkSecurity) && Objects.nonNull(idoLinkSecurity.getLanguage()) ? IdoCodLanguage.getCod(idoLinkSecurity.getLanguage()) : "pt";
			messageTemplate = messageSource.getMessage(messageTemplate, null, Locale.forLanguageTag(languagem));			
		}
        return getObject().interpolate(messageTemplate, context, locale);
	}

}
