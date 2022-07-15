package br.com.idolink.api.execption;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getterpublic class BaseFullException extends RuntimeException{

	private static final long serialVersionUID = -8620996109869261307L;

	HttpStatus status;
	String field;
	String[] params;
	
	
	
	public BaseFullException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}
		
	public BaseFullException(HttpStatus status, String field, String message) {
		super(message);
		this.status = status;
		this.field = field;
	}
	
	public BaseFullException(HttpStatus status, String field, String message, String[] params) {
		super(message);
		this.status = status;
		this.field = field;
		this.params = params;
	}
	
	
}
