package br.com.idolink.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostOfficeOptionalServiceResponse {

	private boolean receiptNotice;
	private boolean ownHand;
	private boolean declaredValue;
			
}
