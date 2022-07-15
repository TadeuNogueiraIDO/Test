package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.response.TimezoneResponse;

public interface TimezoneService {
	
	List<TimezoneResponse> list();
	
}
