package br.com.idolink.api.dto.response;

import java.math.BigDecimal;

import br.com.idolink.api.model.enums.PlanSubscriptionTool;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToolPlanPriceResponse {
	
	private PlanSubscriptionTool planSubscription;
	
	private BigDecimal price;
}
