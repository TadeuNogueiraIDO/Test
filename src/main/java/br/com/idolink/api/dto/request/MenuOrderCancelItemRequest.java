package br.com.idolink.api.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuOrderCancelItemRequest {
	
	@NotNull
	private String items;
	
	@NotNull
	private Boolean stock;
	
	@NotBlank
	private String cancellationReason;
}
