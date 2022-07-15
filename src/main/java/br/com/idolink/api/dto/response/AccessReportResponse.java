package br.com.idolink.api.dto.response;

import java.util.Date;

import br.com.idolink.api.dto.response.ido.IdoProfileResponse;
import br.com.idolink.api.model.enums.ToolCodName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessReportResponse {

	private String ip;
	
	private ToolCodName toolCodName;
	
	private IdoProfileResponse idoId;
	
	private Date accessDate;
		
}
