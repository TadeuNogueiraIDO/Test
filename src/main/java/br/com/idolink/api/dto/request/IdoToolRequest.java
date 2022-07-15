package br.com.idolink.api.dto.request;

import javax.validation.constraints.NotNull;

import br.com.idolink.api.model.enums.IdoToolStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IdoToolRequest {
	
	@NotNull
	private IdoToolStatus status;
	
	@NotNull
	private Long toolId; 
}
