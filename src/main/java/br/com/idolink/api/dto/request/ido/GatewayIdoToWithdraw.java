package br.com.idolink.api.dto.request.ido;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GatewayIdoToWithdraw {
	private String amount;
	private String recipient_id;
}
