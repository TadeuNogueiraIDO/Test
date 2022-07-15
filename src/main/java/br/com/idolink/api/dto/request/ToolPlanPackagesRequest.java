package br.com.idolink.api.dto.request;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.idolink.api.dto.request.common.GenericRequest;
import br.com.idolink.api.model.enums.ToolPlanPackageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToolPlanPackagesRequest {

	@NotNull
	private ToolPlanPackageType toolType;
	
	@NotNull
	@Size(min=1)
	private List<GenericRequest> tools;
	
	@Builder.Default
	private Boolean active = true;
	
	private List<ToolPlanPriceRequest> toolPlanPrice;
}
