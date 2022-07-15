package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.AccessReportRequest;
import br.com.idolink.api.dto.response.AccessReportResponse;
import br.com.idolink.api.model.AccessReport;

@Component
public class AccessReportMapper {
	
	@Autowired
	private ModelMapper mapper;

	public AccessReportResponse response(AccessReport model) {
		return mapper.map(model, AccessReportResponse.class);
	}

	public List<AccessReportResponse> response(List<AccessReport> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}

	public AccessReport model(AccessReportRequest request) {
		return mapper.map(request, AccessReport.class);
	}

	public List<AccessReport> modelList(List<AccessReportRequest> request) {
		return request.stream().map(m -> this.model(m)).collect(Collectors.toList());
	}
}
