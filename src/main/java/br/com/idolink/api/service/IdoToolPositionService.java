package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.IdoToolPositionRequest;
import br.com.idolink.api.dto.response.IdoToolPositionResponse;
import br.com.idolink.api.model.IdoToolPosition;
import br.com.idolink.api.model.enums.ToolCodName;

public interface IdoToolPositionService {

	IdoToolPositionResponse create(IdoToolPositionRequest request, Long idoId);

	List<IdoToolPositionResponse> updateAllPositions(List<IdoToolPositionRequest> positionsRequest, Long idoId);

	IdoToolPositionResponse updatePosition(IdoToolPositionRequest positionRequest, Long idoId);

	void delete(IdoToolPosition toolPosition);

	IdoToolPosition findByIdoToolAndGenericId(Long idoId, ToolCodName toolCodName, Long genericToolId);

	List<IdoToolPositionResponse> findIdoToolPositionByIdo(Long idoId);

	
		
}
