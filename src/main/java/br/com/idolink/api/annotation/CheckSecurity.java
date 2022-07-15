package br.com.idolink.api.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;


public @interface CheckSecurity {
	
	public @interface User {
		@PreAuthorize("isAuthenticated() AND @idolinkSecurity.profileWithoutdebt() == false")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAcessar {
		}
	}

}

