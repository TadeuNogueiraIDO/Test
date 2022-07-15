package br.com.idolink.api.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class IdoContactDialCodeResponse extends IdoContactValueResponse{

	private String dialCode;
}
