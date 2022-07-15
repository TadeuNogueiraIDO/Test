package br.com.idolink.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.idolink.api.dto.request.ToolPlanPackagesRequest;
import br.com.idolink.api.dto.response.ToolPlanPackagesResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.ToolPlanPackageMapper;
import br.com.idolink.api.model.ToolPlanPackage;
import br.com.idolink.api.model.ToolPlanPrice;
import br.com.idolink.api.model.enums.ToolPlanPackageType;
import br.com.idolink.api.repository.ToolPlanPackageRepository;
import br.com.idolink.api.service.ToolPlanPackageService;

@Service
public class ToolPlanPackageServiceImpl implements ToolPlanPackageService{

	@Autowired
	private ToolPlanPackageRepository repository;
	
	@Autowired
	private ToolPlanPackageMapper mapper;
	
	@Override
	@Transactional
	public ToolPlanPackagesResponse create(ToolPlanPackagesRequest request) {
		if(request.getToolType() != ToolPlanPackageType.INDIVIDUAL) {
			valideToolPlan(request.getToolType());
		}
		if(request.getToolPlanPrice() == null) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST,"Price", "api.error.tool.plan.package.price.not.found");
		}else {
			if(request.getToolPlanPrice().size() < 2) {
				throw new BaseFullException(HttpStatus.BAD_REQUEST, "Price","api.error.tool.plan.package.price.month.year.not.found");
			}
		}
		
		ToolPlanPackage model = mapper.requestToModel(request);
		
		
		return mapper.modelToResponse(repository.save(model));
	}
	
	@Transactional
	private void valideToolPlan(ToolPlanPackageType toolType) {
		ToolPlanPackage plan = repository.findByToolType(toolType);
		if(plan != null) {
			plan.setActive(false);
			repository.save(plan);
		}
	}
	
	@Override
	public ToolPlanPackagesResponse findToolPlanPackage(ToolPlanPackageType toolType) {
		ToolPlanPackage model =  repository.findByToolType(toolType);
		
		if(model == null) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "ToolPlanPackages","api.error.tool.plan.package.not.found");
		}
		
		return mapper.modelToResponse(model);
	}
	
	@Override
	public List<ToolPlanPackagesResponse> findAll() {
		List<ToolPlanPackage> model =  repository.findAll();
		return mapper.modelToResponse(model);
	}

	@Override
	@Transactional
	public ToolPlanPackagesResponse update(ToolPlanPackagesRequest request, Long id) {
		ToolPlanPackage model =  repository.findById(id).orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,"ToolPlanPackages", "api.error.tool.plan.package.not.found"));
		
		ToolPlanPackage toSave = mapper.requestToModel(request);
		
		Boolean validPrice  = validPrice(model.getToolPlanPrice(), toSave.getToolPlanPrice(), toSave);
		
		//ATUALIZA E CRIA
		if(validPrice && model.getActive()) {
			model.setActive(false);
			repository.save(model);
		}
		//SÓ ATUALIZA
		else {
			List<ToolPlanPrice> tools = model.getToolPlanPrice();
			BeanUtils.copyProperties(toSave, model, "id", "dateModel");
			toSave = model;
			toSave.setToolPlanPrice(tools);
		}	
		
		return mapper.modelToResponse(repository.save(toSave));
	}
	
	
	private Boolean validPrice(List<ToolPlanPrice> model, List<ToolPlanPrice> updating, ToolPlanPackage toSave) {
		Boolean result = true;
		
		if(updating!= null) {			
			model = model.stream().map(m-> ToolPlanPrice.builder()
					.planSubscription(m.getPlanSubscription())
					.price(m.getPrice())
					.toolPlanPackage(toSave).build()).collect(Collectors.toList());
			
			if(model.equals(updating)) {
				result = false;
			}else {
				if(updating.size()<model.size()) {
					result = false;
				}
			}
		}
		return result;
	}

}
