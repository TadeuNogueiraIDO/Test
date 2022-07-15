package br.com.idolink.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostOfficeOptionalServiceRequest {

	private boolean receiptNotice;
	private boolean ownHand;
	private boolean declaredValue;
			
}
