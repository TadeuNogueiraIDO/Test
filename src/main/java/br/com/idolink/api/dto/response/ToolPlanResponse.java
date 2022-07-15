package br.com.idolink.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToolPlanResponse {
	private Long id;
	private String description;
	private String name;
	private Integer addition;
	private Integer resourceLimitation; 
	
	private String msgTranslator;
}
