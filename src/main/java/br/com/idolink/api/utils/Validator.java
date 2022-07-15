package br.com.idolink.api.utils;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.com.idolink.api.execption.BaseFullException;

public class Validator {

	public static void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
	
	public static void validate(Optional<?> model, String field, String message, HttpStatus status) {

		if (model.isEmpty()) {
			throw new BaseFullException(status, field, message);
		}
	}
}
