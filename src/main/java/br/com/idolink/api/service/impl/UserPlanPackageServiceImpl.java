package br.com.idolink.api.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.idolink.api.dto.request.IdoPlanPackageRequest;
import br.com.idolink.api.dto.request.common.GenericRequest;
import br.com.idolink.api.dto.response.IdoPlanPackageResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.UserPlanPackageMapper;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.IdoTool;
import br.com.idolink.api.model.Tool;
import br.com.idolink.api.model.ToolPlanPackage;
import br.com.idolink.api.model.UserPlanPackage;
import br.com.idolink.api.model.enums.IdoToolStatus;
import br.com.idolink.api.model.enums.PlanPackagePaymentStatus;
import br.com.idolink.api.model.enums.ToolPlanPackageType;
import br.com.idolink.api.repository.IdoRepository;
import br.com.idolink.api.repository.IdoToolRepository;
import br.com.idolink.api.repository.ToolPlanPackageRepository;
import br.com.idolink.api.repository.UserPlanPackageRepository;
import br.com.idolink.api.service.UserPlanPackageService;
import br.com.idolink.api.utils.PlanSubscriptionUtils;

@Service
public class UserPlanPackageServiceImpl implements UserPlanPackageService{

	@Autowired
	private UserPlanPackageRepository repository;
	
	@Autowired
	private ToolPlanPackageRepository planRepository;
	
	@Autowired
	private IdoToolRepository idoToolRepository;
	
	@Autowired
	private IdoRepository idoRepository;
	
	@Autowired
	private UserPlanPackageMapper mapper;
	
	@Override
	@Transactional
	public IdoPlanPackageResponse create(IdoPlanPackageRequest request, Long idoId) {
		
		Optional<Ido> ido = idoRepository.findByIdUserFilter(idoId);

		if (ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND,"Ido" ,"api.error.ido.not.found");
		}
		
		List<IdoTool> idoTools = new ArrayList<>();
		
		ToolPlanPackage plan = validateToolPlanPackage(request.getPlan());
		UserPlanPackage model = repository.findByToolType(idoId, request.getToolType());
		
		if(plan.getToolType() != request.getToolType()) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "PlanPackage", "api.error.tool.plan.package.type.not.found");
		}
		if(request.getToolType() != ToolPlanPackageType.INDIVIDUAL) {			
			if(model != null) {
				throw new BaseFullException(HttpStatus.BAD_REQUEST, "PlanPackage","api.error.tool.plan.package.similar.conflict");
			}
		}else {
			if(model != null) {				
				model.getToolPlanPackage().getToolPackage().forEach(modelPlan ->{
					plan.getToolPackage().forEach(newPlan ->{
						if(newPlan.getTool().getId() == modelPlan.getTool().getId()) {
							throw new BaseFullException(HttpStatus.BAD_REQUEST, "PlanPackage","api.error.tool.plan.package.similar.conflict");
						}
					});
				});
				if(model.getPlanSubscription() == request.getPlanSubscription()) {
					throw new BaseFullException(HttpStatus.BAD_REQUEST, "PlanPackage","api.error.tool.plan.package.similar.conflict");
				}
			}
		}
		
		plan.getToolPackage().forEach(t ->{
			idoTools.add(generateIdoTool(t.getTool(), ido.get()));
		});
		
		model = mapper.requestToModel(request, PlanSubscriptionUtils.generateExpiredDate(request.getPlanSubscription()),
				ido.get(), validateToolPlanPackage(request.getPlan()));
		
		if(idoTools.size() > 0) {			
			idoToolRepository.saveAll(idoTools);
		}
		return mapper.modelToResponse(repository.save(model));
	}
	
	private ToolPlanPackage validateToolPlanPackage(GenericRequest plan) {
		return planRepository.findById(plan.getId())
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "PlanPackage","api.error.tool.plan.package.not.found"));
	}

	private IdoTool generateIdoTool(Tool tool, Ido ido) {
		
		IdoTool idoTool = idoToolRepository.findByIdoAndTool(ido.getId(), tool.getId());
		
		if(Objects.isNull(idoTool)) {
			idoTool = IdoTool.builder()
					.ido(ido)
					.tool(tool)
					.status(IdoToolStatus.BOUGHT)
					.build();
		}
		
		return idoTool;
	}
	
	@Override
	public List<IdoPlanPackageResponse> findAll(Long userId, String idoIds) {
		List<Long> ids = null;
		
		if(!StringUtils.isBlank(idoIds)) {
			ids = Arrays.asList(idoIds.split(",")).stream().map(i -> Long.valueOf(i)).collect(Collectors.toList());
		}
		
		return repository.findAllByUserId(userId, ids).stream()
				.map(p -> mapper.modelToResponse(p)).collect(Collectors.toList());
	}
	
	@SuppressWarnings("unused")
	private UserPlanPackage searchOrFail(Long id) {
		return repository.findById(id).orElseThrow(() -> 
			new BaseFullException(HttpStatus.NOT_FOUND, "PlanPackage","api.error.tool.plan.package.user.log.not.found"));
	}
	
	private UserPlanPackage searchOrFail(Long planId, Long idoId) {
		UserPlanPackage model = repository.findByIdAndIdoId(planId, idoId);
		if(model == null) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "PlanPackage","api.error.tool.plan.package.user.log.not.found");
		}
				
		return model;
	}
	
	@Override
	@Transactional
	public void delete(Long planId, Long idoId) {
		
		UserPlanPackage model = searchOrFail(planId, idoId);
		
		List<IdoTool> idoTools = new ArrayList<>();
		
		model.getToolPlanPackage().getToolPackage().forEach(t ->{
			IdoTool tool = idoToolRepository.findByIdoAndTool(idoId, t.getTool().getId());
			if(tool != null) {
				tool.setStatus(IdoToolStatus.INACTIVATED);
				idoTools.add(tool);
			}
		});
		idoToolRepository.saveAll(idoTools);
		
		if(model.getPaymentStatus() != PlanPackagePaymentStatus.PAID) {
			throw new BaseFullException(HttpStatus.CONFLICT,"PlanPackage" ,"api.error.tool.plan.package.linked.conflict");
		}
		try {
			repository.delete(model);
		}catch (Exception e) {
			throw new BaseFullException(HttpStatus.CONFLICT,"IdoPlanPackageServiceImpl", "api.error.tool.plan.package.user.not.found");
		}
		
	}

}
