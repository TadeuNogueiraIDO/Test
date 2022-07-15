package br.com.idolink.api.dto.request;

import javax.validation.constraints.NotNull;

import br.com.idolink.api.model.enums.ToolCodName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IdoToolPositionRequest {
	
	@NotNull
	private ToolCodName ToolCodName;
	
	@NotNull
	private Long genericToolId;
	
	@NotNull
	private Long position;
}
