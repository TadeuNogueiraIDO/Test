package br.com.idolink.api.config.feign;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class ApiKeyRequestInterceptor implements RequestInterceptor {

	
	//não está coletando a variável de ambiente
	@Value("${pagarme.key.acess}")
	private String pagarMeKey;

	@Override
	public void apply(RequestTemplate template) {
		try {
			template.query("api_key", "ak_test_bUFcikngEGHAUSG0N9QMW3Ad89qGe3");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
