package br.com.idolink.api.dto.response;

import br.com.idolink.api.model.enums.ToolCodName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdoToolPositionResponse {
		
	private Long IdoId;
	
	private ToolCodName ToolCodName;
	
	private Long genericToolId;
	
	private Long position;
}
