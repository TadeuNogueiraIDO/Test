package br.com.idolink.api.dto.request;

import javax.validation.constraints.NotNull;

import br.com.idolink.api.dto.request.common.GenericRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToolConfigPackageRequest {

	@NotNull
	private GenericRequest tool;
	
	private Integer addition;
	
	private String description;

	private Integer resourceLimitation;
}
