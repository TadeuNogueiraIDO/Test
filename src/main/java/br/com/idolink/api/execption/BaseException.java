package br.com.idolink.api.execption;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{

	private static final long serialVersionUID = -8620996109869261307L;

	HttpStatus status;
		
	public BaseException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}
		
}
