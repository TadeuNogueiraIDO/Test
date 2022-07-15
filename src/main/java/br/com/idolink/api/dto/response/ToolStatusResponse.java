package br.com.idolink.api.dto.response;

import br.com.idolink.api.model.enums.IdoToolStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToolStatusResponse {

	private IdoToolStatus status;
	
	private ToolResponse tool;
	
}
