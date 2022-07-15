package br.com.idolink.api.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class IdoContactDialCodeRequest extends IdoContactValueRequest{
		
	private String dialCode;
	
}
