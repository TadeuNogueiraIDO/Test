package br.com.idolink.api.config.feign;

import java.io.IOException;
import java.io.Reader;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.idolink.api.execption.BaseException;
import br.com.idolink.api.execption.handler.Problem;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder{
	
	Logger logger = LoggerFactory.getLogger(FeignErrorDecoder.class);
	
	@SuppressWarnings("deprecation")
	@Override
	public Exception decode(String methodKey, Response response) {

        String erroMessage = null;
        Reader reader = null;
        
        try {
        	if(response.body() != null) {
				reader = response.body().asReader();

				String result = IOUtils.toString(reader);
				ObjectMapper mapper = new ObjectMapper();
				mapper.registerModule(new JavaTimeModule());
				mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
				mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
				Problem exceptionMessage = mapper.readValue(result, Problem.class);
				erroMessage = exceptionMessage.getDetail();
        	}

        } catch (IOException e) {
        	logger.error("IO Exception on reading exception message feign client" + e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                logger.error("IO Exception on reading exception message feign client" + e);
            }
        }
        
		switch (response.status()) {
        case 400:
            return new BaseException(HttpStatus.BAD_REQUEST, erroMessage);
        case 401:
        	return new BaseException(HttpStatus.UNAUTHORIZED, erroMessage);
        case 404:
        	return new BaseException(HttpStatus.NOT_FOUND, erroMessage);
        default:
        	return new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, erroMessage);
    }
	}

}
