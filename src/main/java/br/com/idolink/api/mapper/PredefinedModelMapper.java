package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.api.integration.response.BlobFileResponse;
import br.com.idolink.api.dto.request.PredefinedModelRequest;
import br.com.idolink.api.dto.response.PredefinedModelResponse;
import br.com.idolink.api.model.PredefinedModel;

@Service
public class PredefinedModelMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private StorageApi storageApi;

	public PredefinedModelResponse response(PredefinedModel model) {
		BlobFileResponse image = storageApi.findByUuid(model.getFileUiid());
		PredefinedModelResponse response = mapper.map(model, PredefinedModelResponse.class);
		response.setImage(image);
		return response;
	}

	public List<PredefinedModelResponse> response(List<PredefinedModel> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());		
	}
	
	public PredefinedModel model(PredefinedModelRequest request) {
		return mapper.map(request, PredefinedModel.class);
	}

	/**
	 * converts a PredefinedModelRequest List in PredefinedModel List
	 * @param model
	 * @return
	 */
	public List<PredefinedModel> modelList(List<PredefinedModelRequest> request) {
		return request.stream().map(m -> this.model(m)).collect(Collectors.toList());		
	}
			
}
