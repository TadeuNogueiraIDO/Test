package br.com.idolink.api.dto.response;

import java.util.List;

import br.com.idolink.api.model.enums.ToolPlanPackageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToolPlanPackagesResponse {
	
	private Long id;
	
	private ToolPlanPackageType toolType;
	
	private List<ToolPlanResponse> toolPackage;

	private List<ToolPlanPriceResponse> toolPlanPrice;
	
	private String msgTranslator;
}
