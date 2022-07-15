package br.com.idolink.api.api.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.config.feign.FeignErrorDecoder;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;

@Configuration
public class StorageApiFactory {
	
	@Autowired
	private Environment env;
	
	@SuppressWarnings("deprecation")
	@Bean
	public StorageApi storageApi() {

		String host = env.getProperty("api.storage", String.class);

		Integer connectionTimeout = 10 * 1000;
		Integer readTimeout = 10 * 1000;

		return Feign.builder()
				.options(new Request.Options(connectionTimeout, readTimeout))
				.logger(new Slf4jLogger())
				.encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.errorDecoder(new FeignErrorDecoder())
				.retryer(Retryer.NEVER_RETRY)
				.logLevel(Logger.Level.FULL)
				.target(StorageApi.class, host);

	}
}
