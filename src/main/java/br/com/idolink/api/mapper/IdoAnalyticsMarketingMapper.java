package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.IdoAnalyticsMarketingRequest;
import br.com.idolink.api.dto.response.IdoAnalyticsMarketingResponse;
import br.com.idolink.api.model.IdoAnalyticsMarketing;

@Component
public class IdoAnalyticsMarketingMapper {
	
	@Autowired
	private ModelMapper mapper;

	public IdoAnalyticsMarketingResponse response(IdoAnalyticsMarketing model) {
		return mapper.map(model, IdoAnalyticsMarketingResponse.class);
	}

	public List<IdoAnalyticsMarketingResponse> response(List<IdoAnalyticsMarketing> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}

	public IdoAnalyticsMarketing model(IdoAnalyticsMarketingRequest request) {
		return mapper.map(request, IdoAnalyticsMarketing.class);
	}

	public List<IdoAnalyticsMarketing> modelList(List<IdoAnalyticsMarketingRequest> request) {
		return request.stream().map(m -> this.model(m)).collect(Collectors.toList());
	}
}
