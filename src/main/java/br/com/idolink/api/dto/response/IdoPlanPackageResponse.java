package br.com.idolink.api.dto.response;

import br.com.idolink.api.model.enums.PlanPackagePaymentStatus;
import br.com.idolink.api.model.enums.PlanSubscriptionTool;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IdoPlanPackageResponse {
	
	private Long id; 
	
	private PlanPackageIdoResponse ido;
	
//	private LocalDateTime expirationDate;
	
	private PlanPackagePaymentStatus paymentStatus;
	
	private PlanSubscriptionTool planSubscription;
	
	private ToolPlanPackagesResponse toolPlanPackage;
}
