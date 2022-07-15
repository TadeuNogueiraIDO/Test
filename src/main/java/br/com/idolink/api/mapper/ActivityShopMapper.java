package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.ActivityShopRequest;
import br.com.idolink.api.dto.response.ActivityShopResponse;
import br.com.idolink.api.model.ActivityShop;

@Component
public class ActivityShopMapper {
	@Autowired
	private ModelMapper mapper;

	public ActivityShopResponse response(ActivityShop model) {
		return mapper.map(model, ActivityShopResponse.class);
	}

	public List<ActivityShopResponse> response(List<ActivityShop> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}

	public ActivityShop model(ActivityShopRequest request) {
		return mapper.map(request, ActivityShop.class);
	}

	public List<ActivityShop> modelList(List<ActivityShopRequest> request) {
		return request.stream().map(m -> this.model(m)).collect(Collectors.toList());
	}
}
