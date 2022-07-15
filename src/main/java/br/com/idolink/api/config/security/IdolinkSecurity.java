package br.com.idolink.api.config.security;



import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import br.com.idolink.api.execption.BaseException;

@Component
public class IdolinkSecurity {
	
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public Long getUsuarioId() {
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
		return jwt.getClaim("id");
	}
	
	public Long getLanguage() {
		
		if(Objects.nonNull(getAuthentication())) {
			try {
				Jwt jwt = (Jwt) getAuthentication().getPrincipal();				
				return jwt.getClaim("language");
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}
  
	   public boolean profileWithoutdebt() {
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();
        
        Boolean inDebt = jwt.getClaimAsBoolean("invoiceOverdue");
        if(inDebt) {
        	throw new BaseException(HttpStatus.FORBIDDEN, "Você tem faturas pendentes, não é possível acessar esse recurso.");
        }
        
        return inDebt;
	   }

}