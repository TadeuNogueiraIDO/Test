package br.com.idolink.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.IdoToolPositionRequest;
import br.com.idolink.api.dto.response.IdoToolPositionResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.IdoToolPositionMapper;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.IdoToolPosition;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.IdoRepository;
import br.com.idolink.api.repository.IdoToolPositionRepository;
import br.com.idolink.api.service.IdoToolPositionService;

@Service
public class IdoToolPositionServiceImpl implements IdoToolPositionService {

	@Autowired
	private IdoToolPositionMapper mapper;
	
	@Autowired
	private IdoToolPositionRepository repository; 
	
	@Autowired
	private IdoRepository idoRepository;
	
	@Override
	@Transactional
	public IdoToolPositionResponse create(IdoToolPositionRequest request, Long idoId) {
		
		Optional<Ido> ido = idoRepository.findById(idoId);
		
		validate(ido, "Ido", "api.error.ido.not.found");
		
		Long lastPosition = repository.findLastPosition(idoId);
		
		if(Objects.isNull(lastPosition)) {
			lastPosition = 0L;
		}
		
		IdoToolPosition toolPosition = IdoToolPosition.builder()
				.ido(ido.get()).toolCodName(request.getToolCodName())
				.position(lastPosition + 1)
				.genericToolId(request.getGenericToolId()).build();
		
		toolPosition = repository.save(toolPosition);
		return mapper.modelToResponse(toolPosition);
				
	}
		
	@Override
	@Transactional
	public List<IdoToolPositionResponse> updateAllPositions(List<IdoToolPositionRequest>  positionsRequest, Long idoId) {
		
		List<IdoToolPositionResponse> responses = new ArrayList<IdoToolPositionResponse>();
		
		validateRepeatedPosition(positionsRequest);
		
		for (IdoToolPositionRequest idoToolPositionRequest : positionsRequest) {
			responses.add(updatePosition( idoToolPositionRequest,idoId)); 
		}
			
		return responses;
	}
	
	@Override
	public List<IdoToolPositionResponse> findIdoToolPositionByIdo(Long idoId){
		
		Optional<Ido> ido = idoRepository.findById(idoId);
		validate(ido, "Ido", "api.error.ido.not.found");
		
		List<IdoToolPosition> positions = repository.findByIdoOrderByPosition(ido.get());
		
		return mapper.modelToResponse(positions);
			
	}
	
	
	
	@Override
	@Transactional
	public IdoToolPosition findByIdoToolAndGenericId(Long idoId, ToolCodName toolCodName, Long genericToolId) {
		return  repository.findByIdoToolAndGenericId(idoId, toolCodName, genericToolId);
	}
	
	@Override
	@Transactional
	public IdoToolPositionResponse updatePosition(IdoToolPositionRequest positionRequest, Long idoId) {
		
		IdoToolPosition toolPosition = repository.findByIdoToolAndGenericId(idoId, positionRequest.getToolCodName(), positionRequest.getGenericToolId());
		
		if(Objects.nonNull(toolPosition)) {
			
			toolPosition.setPosition(positionRequest.getPosition());
			toolPosition = repository.save(toolPosition);
			return mapper.modelToResponse(toolPosition);
			
		}
			
		return new IdoToolPositionResponse();
	}

	@Override
	@Transactional
	public void delete(IdoToolPosition toolPosition) {
		
		Long position = toolPosition.getPosition();
		
		List<IdoToolPosition> idoToolPositions = repository.findByIdoByPosition(toolPosition.getIdo().getId(), position);  
		
		for (IdoToolPosition idoToolPosition : idoToolPositions) {
			idoToolPosition.setPosition(idoToolPosition.getPosition()-1);
			repository.save(idoToolPosition);
		}
		
		repository.delete(toolPosition);
	}
	
	private void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
	
	private void validateRepeatedPosition(List<IdoToolPositionRequest>  positionsRequest) {
		List<Long> positions = new ArrayList<>(); 
		positionsRequest.stream().forEach(position -> positions.add(position.getPosition()));
		
		Stream<Long> positionsDistinct = positions.stream().distinct();
		
		if(positions.size()!= positionsDistinct.count()) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Position", "api.error.ido.tool.position.conflict");
		}
				
	}
	
	
	
	
	
	
	
}
