package br.com.idolink.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.IdoToolRequest;
import br.com.idolink.api.dto.response.IdoToolResponse;
import br.com.idolink.api.dto.response.ToolStatusListResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.filter.ToolFilter;
import br.com.idolink.api.mapper.IdoToolMapper;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.IdoTool;
import br.com.idolink.api.model.Tool;
import br.com.idolink.api.model.enums.IdoToolStatus;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.IdoRepository;
import br.com.idolink.api.repository.IdoToolRepository;
import br.com.idolink.api.repository.ToolRepository;
import br.com.idolink.api.service.IdoToolService;
import br.com.idolink.api.service.ToolService;

@Service
public class IdoToolServiceImpl implements IdoToolService {

	@Autowired
	private IdoToolMapper mapper;
	
	@Autowired
	private IdoToolRepository repository;
	
	@Autowired
	private IdoRepository idoRepository;
	
	@Autowired
	private ToolRepository toolRepository;
	
	@Autowired
	private ToolService toolService;
	
		
	@Override
	@Transactional
	public IdoToolResponse create(Long idoId, IdoToolRequest request) {
		
		Optional<Ido> ido =  idoRepository.findByIdUserFilter(idoId);
		validate(ido, "Ido", "api.error.ido.not.found");
		
		Optional<Tool> tool = toolRepository.findById(request.getToolId());
		validate(tool, "Tool", "api.error.ido.tool.not.found");
		
		if(!tool.get().getReuse()) {
		
			List<IdoTool> list = repository.findByIdoAndTool(ido.get(), tool.get());
						
			if(!list.isEmpty()) {
				throw new BaseFullException(HttpStatus.CONFLICT, "Tool", "api.error.ido.tool.conflict.not.reuse");
			}
		}
		
		IdoTool model = mapper.requestToModel(request);
		model.setIdo(ido.get());
		model.setTool(tool.get());
		model = repository.save(model);
		return mapper.modelToResponse(model);
		
	}

	@Override
	@Transactional
	public IdoToolResponse update(Long id, IdoToolStatus request) {
		
		Optional<IdoTool> idoToolBase = repository.findById(id);
		validate(idoToolBase, "IdoTool", "api.error.ido.tool.association.not.found");
			
		IdoTool idoTool = idoToolBase.get();
		
		idoTool.setStatus(request);
		idoTool = repository.save(idoTool);
		return mapper.modelToResponse(idoTool);
		
	}

	@Override
	public IdoToolResponse findById(Long id) {

		Optional<IdoTool> tool = repository.findById(id);
		validate(tool, "Idotool", "api.error.ido.tool.association.not.found");
		return mapper.modelToResponse(tool.get());
	}

	/**
	 * Busca todas as ferramentas. O ido em parâmetro eh para buscar as ferramentas utilizadas no ido
	 * @param idoId
	 * @param toolId
	 * @return
	 */
	@Override
	public ToolStatusListResponse findAllByIdo(Long idoId, ToolFilter filter) {
		
		List<IdoTool> model = new ArrayList<IdoTool>();
						
		Optional<Ido> ido =  idoRepository.findByIdUserFilter(idoId);
		
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
				
		List<IdoTool> idoList = repository.findByIdo(idoId);
		
		//Adiciona as ferramentas ja associadas ao ido
//		if(!idoList.isEmpty()) {
//			model.addAll(idoList);
//		}
		
		List<Tool> tools = toolRepository.findAll(filter, true);
				
		//Caso nao exista relacionamento do ido com a ferramenta, um novo objeto eh criado e adicionado a lista
		for (Tool tool : tools) {
			IdoTool toObj = idoList.stream()
					  .filter(idoTo -> tool.getCodName().equals(idoTo.getTool().getCodName()))
					  .findAny()
					  .orElse(null);
			
			if(toObj == null) {
				toObj = IdoTool.builder().ido(ido.get()).tool(tool).status(tool.getAvailability()).build();
			}
			model.add(toObj);					
		}
		
		List<IdoToolResponse> list = mapper.modelToResponse(model).stream().filter(value -> value.getTool().getId() != 14L && value.getTool().getId() != 16L).collect(Collectors.toList());
		
		return  mapper.responseToToolStatusListResponse(list);   
	}
	
	@Override
	public ToolStatusListResponse findByIdoAndTool(Long idoId, Long toolId ) {
		
		Optional<Ido> ido =  idoRepository.findByIdUserFilter(idoId);
		validate(ido, "Ido", "api.error.ido.not.found");
		
		Optional<Tool> tool = toolRepository.findById(toolId);
		validate(tool, "Tool", "api.error.ido.tool.not.found");
		
		List<IdoTool> model = repository.findByIdoAndTool(ido.get(), tool.get());
		
		List<IdoToolResponse> list = mapper.modelToResponse(model);
			
		return mapper.responseToToolStatusListResponse(list);   
	}
	
	@Override
	public List<IdoTool> findListIdoAndTool(Long idoId, Long toolId ) {
		
		Optional<Ido> ido =  idoRepository.findByIdUserFilter(idoId);
		validate(ido, "Ido", "api.error.ido.not.found");
		
		Optional<Tool> tool = toolRepository.findById(toolId);
		validate(tool, "Tool", "api.error.ido.tool.not.found");
		
		List<IdoTool> model = repository.findByIdoAndTool(ido.get(), tool.get());
							
		return model;   
	}
	
	@Override
	public IdoTool findIdoAndTool(Long idoId, Long toolId ) {
		
		Optional<Ido> ido =  idoRepository.findByIdUserFilter(idoId);
		validate(ido, "Ido", "api.error.ido.not.found");
		
		Optional<Tool> tool = toolRepository.findById(toolId);
		validate(tool, "Tool", "api.error.ido.tool.not.found");
		
		IdoTool model = repository.findByIdoAndTool(idoId,toolId);
							
		return model;   
	}
	
	
	@Override
	@Transactional
	public void delete(Long id) {
		
		Optional<IdoTool> tool = repository.findById(id);
		validate(tool, "Tool", "api.error.tool.not.found");
		
		try {
			repository.delete(tool.get());
			repository.flush();
		} catch(DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.CONFLICT, "Association", "api.error.ido.tool.association.conflict");
		}
		
	}
	
	/**
	 * cria ou atualiza um relacionamento entre a ferramenta e o ido após a ferramenta ser criada
	 */
	@Override
	@Transactional
	public void saveAssociateTool(ToolCodName toolCodName, Long idoId) {
		
		Tool tool = toolService.findByCodName(toolCodName);
		
		List<IdoTool> idoTools = this.findListIdoAndTool(idoId, tool.getId());
		
		if(idoTools == null || idoTools.isEmpty() ) {
			this.create(idoId, IdoToolRequest.builder().toolId(tool.getId()).status(IdoToolStatus.INSTALLED).build());
		}
		else {
			for (IdoTool idoTool : idoTools) {
				this.update(idoTool.getId(), IdoToolStatus.INSTALLED);
			}		
		}
	}
	
	/**
	 * ferramenta volta a esta disponivel apos ser deletada do ido
	 * @param toolCodName
	 * @param idoId
	 */
	@Override
	@Transactional
	public void avaliableAssociateIdoTool(ToolCodName toolCodName, Long idoId, Long qtd) {
		
		Tool tool = toolService.findByCodName(toolCodName);
			
		List<IdoTool> idoTools = this.findListIdoAndTool(idoId, tool.getId());
		
		if(tool.getReuse()) {
			if(qtd != null && qtd <= 0) {
				for (IdoTool idoTool : idoTools) {
					this.update(idoTool.getId(), idoTool.getTool().getAvailability());
				}	
			}
						
		}else {
			for (IdoTool idoTool : idoTools) {
				this.update(idoTool.getId(), idoTool.getTool().getAvailability());
			}	
		}	
				
	}
	
	/**
	 * ferramenta volta a esta disponivel apos ser deletada do ido
	 * @param toolCodName
	 * @param idoId
	 */
	@Override
	@Transactional
	public void avaliableAssociateIdoTool(ToolCodName toolCodName, Long idoId) {
		
		this.avaliableAssociateIdoTool(toolCodName, idoId, null);	
		
	}
	
	
	private void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
			
}
