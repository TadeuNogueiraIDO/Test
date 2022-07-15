package br.com.idolink.api.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.idolink.api.model.enums.ToolCodName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClicksToolsResponse {

	@JsonIgnore
	private Long totalVisitors;
	
	@JsonIgnore
	private Long genericToolsId;
	
	@JsonIgnore
	private ToolCodName toolCodName;
	
	private String description;
	
	private Long percentsVisitors;

	public ClicksToolsResponse(Long totalVisitors, Long genericToolsId, ToolCodName toolCodName) {
		super();
		this.totalVisitors = totalVisitors;
		this.genericToolsId = genericToolsId;
		this.toolCodName = toolCodName;
	}
	
	
	
	
}
