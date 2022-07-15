package br.com.idolink.api.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.idolink.api.dto.request.common.GenericRequest;
import br.com.idolink.api.model.enums.PlanSubscriptionTool;
import br.com.idolink.api.model.enums.ToolPlanPackageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IdoPlanPackageRequest {

	@Valid
	@NotNull
	private GenericRequest plan;
	
	@NotNull
	private PlanSubscriptionTool planSubscription;
	
	@NotNull
	private ToolPlanPackageType toolType;
}
