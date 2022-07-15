package br.com.idolink.api.dto.response;

import java.util.List;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import br.com.idolink.api.model.enums.IdoToolStatus;
import br.com.idolink.api.model.enums.ToolCodName;
import lombok.Data;

@Data
public class ToolResponse {

	private Long id;
	
	private String name;
	
	private Boolean active;
	
	private String description;
	
	private Boolean reuse;

	private IdoToolStatus availability;
	
	private BlobFileResponse icon;
	
	private List<ToolCurrencyResponse> prices;
	
	private String url;
	
	private String appversion;
	
	private ToolCodName codName;
	
	private Long numberTestDays;
}
