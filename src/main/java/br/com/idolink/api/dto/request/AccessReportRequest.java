package br.com.idolink.api.dto.request;

import br.com.idolink.api.model.enums.ToolCodName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessReportRequest {

	private String ip;
	
	private ToolCodName toolCodName;
	
	private Long ido;
	
	private Long genericToolsId;
	
		
}
