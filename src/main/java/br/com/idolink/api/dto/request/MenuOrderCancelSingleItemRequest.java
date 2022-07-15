package br.com.idolink.api.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuOrderCancelSingleItemRequest {
	
	private String items;
	
	private Boolean stock;
	
	@NotBlank
	private String cancellationReason;

}
