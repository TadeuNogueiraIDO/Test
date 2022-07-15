package br.com.idolink.api.dto.request;

import br.com.idolink.api.model.enums.IdoToolStatus;
import lombok.Data;

@Data
public class IdoToolStatusRequest {
	
	private IdoToolStatus status;
	
}
