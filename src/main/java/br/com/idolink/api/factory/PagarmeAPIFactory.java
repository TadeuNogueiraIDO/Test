package br.com.idolink.api.factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import br.com.idolink.api.api.PagarmeAPI;
import br.com.idolink.api.config.feign.ApiKeyRequestInterceptor;
import br.com.idolink.api.config.feign.FeignErrorDecoder;
import br.com.idolink.api.utils.Environments;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;

@Configuration
public class PagarmeAPIFactory {

    @Autowired
    private Environment env;

    @SuppressWarnings("deprecation")
	@Bean
    public PagarmeAPI PagarmeAPI() {

        String host = env.getProperty(Environments.PAGARME_HOST, String.class);

        Integer connectionTimeout = env.getProperty(Environments.PAGARME_CONNECT_TIMEOUT, Integer.class) * 1000;
        Integer readTimeout = env.getProperty(Environments.PAGARME_READ_TIMEOUT, Integer.class) * 1000;

        return Feign.builder()
            .options(new Request.Options(connectionTimeout, readTimeout))
            .logger(new Slf4jLogger())
            .requestInterceptor(new ApiKeyRequestInterceptor())
            .encoder(new JacksonEncoder())
            .decoder(new JacksonDecoder())
            .errorDecoder(new FeignErrorDecoder())
            .retryer(Retryer.NEVER_RETRY)
            .logLevel(Logger.Level.FULL)
            .target(PagarmeAPI.class, host);

    }

}