package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.AnalyticsMarketingRequest;
import br.com.idolink.api.dto.response.AnalyticsMarketingResponse;
import br.com.idolink.api.model.AnalyticsMarketing;

@Component
public class AnalyticsMarketingMapper {
	
	@Autowired
	private ModelMapper mapper;

	public AnalyticsMarketingResponse response(AnalyticsMarketing model) {
		return mapper.map(model, AnalyticsMarketingResponse.class);
	}

	public List<AnalyticsMarketingResponse> response(List<AnalyticsMarketing> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}

	public AnalyticsMarketing model(AnalyticsMarketingRequest request) {
		return mapper.map(request, AnalyticsMarketing.class);
	}

	public List<AnalyticsMarketing> modelList(List<AnalyticsMarketingRequest> request) {
		return request.stream().map(m -> this.model(m)).collect(Collectors.toList());
	}
}
