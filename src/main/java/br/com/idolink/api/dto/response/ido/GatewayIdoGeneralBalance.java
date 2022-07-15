package br.com.idolink.api.dto.response.ido;

import br.com.idolink.api.dto.response.common.GenericResponseAmout;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GatewayIdoGeneralBalance {
	
	private GenericResponseAmout waiting_funds;
	
	private GenericResponseAmout transferred;
	
	private GenericResponseAmout available;
}
