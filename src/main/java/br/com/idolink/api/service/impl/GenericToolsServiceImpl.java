package br.com.idolink.api.service.impl;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.IdoToolPositionRequest;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.model.IdoToolPosition;
import br.com.idolink.api.model.ToolConfigPackage;
import br.com.idolink.api.model.ToolPlanPackage;
import br.com.idolink.api.model.enums.PlanPackagePaymentStatus;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.model.enums.ToolPlanPackageType;
import br.com.idolink.api.repository.ToolConfigPackageRepository;
import br.com.idolink.api.repository.ToolPlanPackageRepository;
import br.com.idolink.api.service.IdoToolPositionService;
import br.com.idolink.api.service.IdoToolService;

@Service
public class GenericToolsServiceImpl{
	
	@Autowired
	private IdoToolService idoToolService;
	
	@Autowired
	private IdoToolPositionService idoToolPositionService;
	
	@Autowired
	private ToolConfigPackageRepository configPackageRepository; 
	
	@Autowired
	private ToolPlanPackageRepository toolPlanPackageRepository;
	
	public void validToolsUserResource(Long idoId, ToolCodName codName, Long currentQt) {
		validToolsUser(idoId, codName, currentQt, false, true);
	}
	
	public void validToolsUserAddition(Long idoId, ToolCodName codName, Long currentQt) {
		validToolsUser(idoId, codName, currentQt, true, false);
	}
	
	public void validToolsUser(Long idoId, ToolCodName codName, Long currentQt, Boolean validAddition, Boolean validResource) {
		
		Boolean msg = true;
		
		List<ToolConfigPackage> toolConfigPackages = configPackageRepository.findByIdo(idoId, PlanPackagePaymentStatus.PAID);
		
				
		if (!toolConfigPackages.isEmpty()) {

			// verifica qual plano ele tem e se a ferramenta esta no plano
			for (ToolConfigPackage tool : toolConfigPackages) {
				if (Objects.nonNull(tool.getTool()) && tool.getTool().getCodName().equals(codName)) {
					if (validAddition) {
						if (Objects.isNull(tool.getAddition()) || tool.getAddition() == 0
								|| currentQt <= tool.getAddition()) {
							msg = false;
						}
					}
					if (validResource) {
						if (Objects.isNull(tool.getResourceLimitation()) || tool.getResourceLimitation() == 0
								|| currentQt <= tool.getResourceLimitation()) {
							msg = false;
						}
					}
				}
			}
			
		}else {
			
			//se o usuario nao tem plano e so utiliza o plano gratuito
			ToolPlanPackage freepackage  = toolPlanPackageRepository.findByToolType(ToolPlanPackageType.FREE);
			
			if(Objects.nonNull(freepackage)) {
				for (ToolConfigPackage tool : freepackage.getToolPackage()) {
					if(Objects.nonNull(tool.getTool()) && tool.getTool().getCodName().equals(codName)) {
						if(validAddition) {
							if(Objects.isNull(tool.getAddition()) || tool.getAddition() == 0 || currentQt <= tool.getAddition()) {
								msg = false;
							}	
						}
						if(validResource) {
							if(Objects.isNull(tool.getResourceLimitation()) || tool.getResourceLimitation() == 0 || currentQt <= tool.getResourceLimitation()) {
								msg = false;
							}	
						}
					}
				}
			}
		}
		
		if(msg) {
			throw new BaseFullException(HttpStatus.FORBIDDEN, "Tool", String.format("api.error.tool.limite.conflict"));
		}
	}	
	
	
	@Transactional
	public void createTools(ToolCodName codName, Long idoId, Long idTool) {
		
		//adiciona a associacao a ferramenta
		idoToolService.saveAssociateTool(codName, idoId);
				
		IdoToolPositionRequest request = IdoToolPositionRequest
				.builder().genericToolId(idTool)
				.ToolCodName(codName).genericToolId(idTool).build();
				
		//adiciona a lista de ferramenta de acordo a posição
		idoToolPositionService.create(request,idoId);
	}
	
	@Transactional
	public void createTools(ToolCodName codName, Long idoId, Long idTool, Long qtd) {
		
		//valida a ferramenta de acordo com o plano
		validToolsUserAddition(idoId, codName, qtd);
		
		createTools(codName, idoId, idTool);
	}

	@Transactional
	public void deleteTools(ToolCodName codName, Long idoId, Long idTool, Long qtd) {
		
		if(Objects.nonNull(qtd)) {
			idoToolService.avaliableAssociateIdoTool(codName,idoId, qtd);	
		}else {
			idoToolService.avaliableAssociateIdoTool(codName, idoId);
		}
		
		IdoToolPosition idoToolPosition = idoToolPositionService.findByIdoToolAndGenericId(idoId, codName, idTool); 
		
		if(Objects.nonNull(idoToolPosition)) {
			idoToolPositionService.delete(idoToolPosition);
		}
				
	}
			
}
