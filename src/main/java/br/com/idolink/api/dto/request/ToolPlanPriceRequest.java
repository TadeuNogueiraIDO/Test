package br.com.idolink.api.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import br.com.idolink.api.model.enums.PlanSubscriptionTool;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToolPlanPriceRequest {

	@NotNull
	private PlanSubscriptionTool planSubscription;
	
	@NotNull
	private BigDecimal price;
}
