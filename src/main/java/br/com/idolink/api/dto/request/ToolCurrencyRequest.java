package br.com.idolink.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToolCurrencyRequest {

	private ToolRequest tool;
	
	private CurrencyRequest currency;
	
	private Double price;
	
}
