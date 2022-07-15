package br.com.idolink.api.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.idolink.api.dto.request.common.GenericRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityShopRequest {
	
	@Valid
	@NotNull
	private GenericRequest TypeActivity;
	
	private String otherSegment;
		
}
