package br.com.idolink.api.dto.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.idolink.api.model.enums.IdoToolStatus;
import br.com.idolink.api.model.enums.ToolCodName;
import lombok.Data;

@Data
public class ToolRequest {

	@NotBlank
	private String name;
	
	@NotNull
	private Boolean active;
	
	@NotBlank
	private String description;
	
	@NotNull
	private IdoToolStatus availability;
	
	@NotNull
	private Boolean reuse;
	
	@NotNull
	private Long icon;
	
	private List<CurrencyRequest> toolCoins;
	
	private String url;
	
	private String appversion;
	
	private ToolCodName codName;
	
	private Long numberTestDays;
}
