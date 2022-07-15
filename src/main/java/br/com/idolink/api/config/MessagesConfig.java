package br.com.idolink.api.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessagesConfig {

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:/messages/system/messages",
				"classpath:/messages/category/messages", 
				"classpath:/messages/country/messages",
				"classpath:/messages/payment/messages",
				"classpath:/messages/activities/messages",
				"classpath:/messages/tools/messages");
		
		//messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

}