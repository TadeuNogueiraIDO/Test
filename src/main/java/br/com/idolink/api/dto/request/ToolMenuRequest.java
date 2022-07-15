package br.com.idolink.api.dto.request;

import javax.validation.constraints.NotBlank;

import br.com.idolink.api.dto.request.common.GenericRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToolMenuRequest {

	private GenericRequest tool;

	@NotBlank
	private String label;

}
