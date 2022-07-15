package br.com.idolink.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoliciesTermsUseResponse {
	
	private String privacy;
	
	private String reimbursement; 
	
	private String use;
	
	private Long shopId;
	
	private String basePrivacy;
	
	private String baseUse;
	
	private String baseReimbursement;
	
}
