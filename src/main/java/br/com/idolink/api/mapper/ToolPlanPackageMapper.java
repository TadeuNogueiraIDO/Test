package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.ToolPlanPackagesRequest;
import br.com.idolink.api.dto.request.ToolPlanPriceRequest;
import br.com.idolink.api.dto.response.ToolPlanPackagesResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.model.ToolConfigPackage;
import br.com.idolink.api.model.ToolPlanPackage;
import br.com.idolink.api.model.ToolPlanPrice;
import br.com.idolink.api.model.enums.ToolPlanPackageType;
import br.com.idolink.api.repository.ToolConfigPackageRepository;
import br.com.idolink.api.service.MessagePropertieService;

@Component
public class ToolPlanPackageMapper {

	@Autowired
	private ToolConfigPackageRepository toolPackageRepository;
	
	@Autowired
	private ToolPackageMapper toolPackageMapper;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private MessagePropertieService msgService;
	
	
	public ToolPlanPackage requestToModel(ToolPlanPackagesRequest request) {
		ToolPlanPackage model = ToolPlanPackage.builder()
				.active(true)
				.toolPackage(
						request.getTools().stream()
						.map(t -> valideToolPackages(t.getId()))
						.collect(Collectors.toList()))
				.toolType(request.getToolType())
				.build();
		
		model.setToolPlanPrice(toolPriceRequestToModel(request.getToolPlanPrice(), model));
		return model;	
	}
	
	public ToolPlanPackagesResponse modelToResponse(ToolPlanPackage model) {
		ToolPlanPackagesResponse response = mapper.map(model, ToolPlanPackagesResponse.class);
		response.setToolPackage(model.getToolPackage().stream()
				.map(t -> toolPackageMapper.modelToResponse(t)).collect(Collectors.toList()));
		setMsgTranslator(response);
		return response;
	}
	
	public List<ToolPlanPackagesResponse> modelToResponse(List<ToolPlanPackage> model) {
		return model.stream().map(m -> modelToResponse(m)).collect(Collectors.toList());
	}
	
	public ToolConfigPackage valideToolPackages(Long id) {
		return toolPackageRepository.findById(id)
				.orElseThrow(()-> new BaseFullException(HttpStatus.NOT_FOUND, "Tools","api.error.tool.plan.package.not.found"));
	}
	
	public List<ToolPlanPrice> toolPriceRequestToModel(List<ToolPlanPriceRequest> request, ToolPlanPackage toolPlan){
		return request.stream().map(p ->
			ToolPlanPrice.builder()
				.planSubscription(p.getPlanSubscription())
				.price(p.getPrice())
				.toolPlanPackage(toolPlan)
				.build()).collect(Collectors.toList());
				
	}
	
	/**
	 * carrega a msg padrao de traducao do pacote
	 * @return
	 */
	private void setMsgTranslator(ToolPlanPackagesResponse response) {
		
		if(response.getToolType().equals(ToolPlanPackageType.BASIC)) {
			response.setMsgTranslator(msgService.getMessageAttribute("api.model.tool.plan.package.msg.basic"));
		}else if (response.getToolType().equals(ToolPlanPackageType.PREMIUM)) {
			response.setMsgTranslator(msgService.getMessageAttribute("api.model.tool.plan.package.msg.premium"));
		}
	}
	
}
