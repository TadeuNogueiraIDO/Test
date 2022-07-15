package br.com.idolink.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.idolink.api.model.enums.QuickPaySedingStatus;
import br.com.idolink.api.model.enums.TypeShipping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuOrderDeliveryResponse {
	
	private QuickPaySedingStatus statusSeding;
	
	private TypeShipping typeFreight;
	
	private String freight;
	
	private String clientName;
	
	private String clientEmail;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "### #######-####")
	private String clientPhone;
	
	private String clientAddress;
		
}
