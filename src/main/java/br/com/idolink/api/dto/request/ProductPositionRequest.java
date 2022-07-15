package br.com.idolink.api.dto.request;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductPositionRequest {
	
	@NotNull
	private Long position;
	
	@NotNull
	private Long shopProductId;

}