package br.com.idolink.api.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoliciesTermsUseRequest {
	
	private String additionalTextUse;
	
	private String additionalTextReimbursement;
	
	private String additionalTextPrivacy;
	
}
