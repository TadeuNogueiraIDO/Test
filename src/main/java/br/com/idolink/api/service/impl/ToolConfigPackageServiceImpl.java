package br.com.idolink.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.idolink.api.dto.request.ToolConfigPackageRequest;
import br.com.idolink.api.dto.response.ToolPlanResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.ToolPackageMapper;
import br.com.idolink.api.model.Tool;
import br.com.idolink.api.model.ToolConfigPackage;
import br.com.idolink.api.model.ToolPlanPackage;
import br.com.idolink.api.model.ToolPlanPrice;
import br.com.idolink.api.repository.ToolConfigPackageRepository;
import br.com.idolink.api.repository.ToolPlanPackageRepository;
import br.com.idolink.api.repository.ToolRepository;
import br.com.idolink.api.service.ToolConfigPackageService;

@Service
public class ToolConfigPackageServiceImpl implements ToolConfigPackageService{

	@Autowired
	private ToolConfigPackageRepository repository;
	
	@Autowired
	private ToolPackageMapper mapper;
	
	@Autowired
	private ToolRepository toolRepository;
		
	@Autowired
	private ToolPlanPackageRepository toolPlanPackageRepository;
	
	
	@Override
	@Transactional
	public ToolPlanResponse create(ToolConfigPackageRequest request) {
		ToolConfigPackage model = mapper.requestToModel(request);
		
		Tool tool = toolRepository.findById(request.getTool().getId()).orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "Tool",  "api.error.ido.tool.not.found"));
		model.setTool(tool);
		
		return mapper.modelToResponse(repository.save(model));
	}

	@Override
	public List<ToolPlanResponse> findAll() {
		return repository.findAll().stream().map(t -> mapper.modelToResponse(t)).collect(Collectors.toList());
	}

	@Override
	public ToolPlanResponse findById(Long id) {
		ToolConfigPackage model = searchOrFail(id);

		return mapper.modelToResponse(model);
	}

	//
	@Override
	@Transactional
	public ToolPlanResponse update(ToolConfigPackageRequest request, Long id) {
		ToolConfigPackage model = searchOrFail(id);
		
		ToolConfigPackage updating = mapper.requestToModel(request);

		Tool tool = toolRepository.findById(request.getTool().getId()).orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "Tool",  "api.error.ido.tool.not.found"));
		updating.setTool(tool);
		
		if(model.getPackages()!=null && model.getPackages().size() > 0) {
			updating = repository.save(updating);
			if(validToolConfigPackage(model, updating)) {	
				generatePlanPackages(model.getPackages(), updating, id);
			}
		}else {
			BeanUtils.copyProperties(updating, model, "id", "dateModel");
			updating = repository.save(model);
		}
		
		return mapper.modelToResponse(updating);
	}

	private ToolConfigPackage searchOrFail(Long id) {
		ToolConfigPackage model = repository.
				findById(id).orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,"ToolConfigPackage", "api.error.tool.package.not.found"));
		return model;
	}
	
	@Transactional
	private void generatePlanPackages(List<ToolPlanPackage> toolPlans, ToolConfigPackage toolConfig, Long id){
		List<ToolPlanPackage> planPackagesNew = new ArrayList<>();
		
		List<ToolPlanPackage> planPackagesUpdate = new ArrayList<>();
		if(toolPlans != null) {			
			toolPlans.forEach(t->{
				ToolPlanPackage toolPlanNew = copyProperties(t, toolConfig, id);
				if(t.getActive()) {
					t.setActive(false);
					planPackagesUpdate.add(t);
				}
				planPackagesNew.add(toolPlanNew);
			});
		}
		if(planPackagesNew.size() > 0) {
			toolPlanPackageRepository.saveAll(planPackagesNew);			
		}
		if(planPackagesUpdate.size() >0 ) {
			toolPlanPackageRepository.saveAll(planPackagesUpdate);	
		}
	}
	
	private Boolean validToolConfigPackage(ToolConfigPackage model, ToolConfigPackage updating) {
		Boolean valid = true;
		
		ToolConfigPackage newModel = new ToolConfigPackage();
		BeanUtils.copyProperties(model, newModel , "id" , "dateModel", "packages");
		
		if(newModel.equals(updating)) {
			return false;
		}	
		return valid;
	}

	
	private ToolPlanPackage copyProperties(ToolPlanPackage model, ToolConfigPackage toolConfig, Long id) {
		List<ToolConfigPackage> packages = new ArrayList<>();
		
		ToolPlanPackage newPlan =  ToolPlanPackage.builder()
			.toolType(model.getToolType())
			.active(model.getActive())
			.build();
		
		model.getToolPackage().forEach(p ->{
			if(p.getId() == id) {
				packages.add(toolConfig);
			}else {
				packages.add(p);
			}
		});
		
		newPlan.setToolPackage(packages);
		
		newPlan.setToolPlanPrice(model.getToolPlanPrice().stream().map(p -> generatePlanPrice(newPlan, p)).collect(Collectors.toList()));
		
		return newPlan;
	}
	
	
	private ToolPlanPrice generatePlanPrice(ToolPlanPackage newPlan, ToolPlanPrice planPrice) {
		return ToolPlanPrice.builder()
				.planSubscription(planPrice.getPlanSubscription())
				.price(planPrice.getPrice())
				.toolPlanPackage(newPlan)
				.build();
	}
	
	@Override
	public void delete(Long id) {
		try {
			ToolConfigPackage model = searchOrFail(id);
			repository.delete(model);
		} catch (Exception e) {
			throw new BaseFullException(HttpStatus.CONFLICT, "","api.error.tool.plan.package.delete.conflict");
		}
	}

}
