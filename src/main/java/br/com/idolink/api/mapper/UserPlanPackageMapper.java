package br.com.idolink.api.mapper;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.IdoPlanPackageRequest;
import br.com.idolink.api.dto.response.IdoPlanPackageResponse;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.ToolPlanPackage;
import br.com.idolink.api.model.UserPlanPackage;
import br.com.idolink.api.model.enums.PlanPackagePaymentStatus;

@Component
public class UserPlanPackageMapper {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ToolPackageMapper toolPackageMapper;
	
	public UserPlanPackage requestToModel(IdoPlanPackageRequest request, LocalDate expirationDate, Ido ido, ToolPlanPackage toolPackage) {
		return UserPlanPackage.builder()
			.expirationDate(expirationDate)
			.ido(ido)
			.paymentStatus(PlanPackagePaymentStatus.PAID)
			.planSubscription(request.getPlanSubscription())
			.toolPlanPackage(toolPackage)
			.toolType(request.getToolType()).build();

	}
	
	public IdoPlanPackageResponse modelToResponse(UserPlanPackage model) {
		IdoPlanPackageResponse response =  mapper.map(model, IdoPlanPackageResponse.class);
				response.getToolPlanPackage().setToolPackage((model.getToolPlanPackage().getToolPackage().stream()
						.map(t -> toolPackageMapper.modelToResponse(t)).collect(Collectors.toList())));
				
		return response;
	}
}
