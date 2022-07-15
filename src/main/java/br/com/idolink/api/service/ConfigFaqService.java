package br.com.idolink.api.service;

import br.com.idolink.api.dto.request.ConfigFaqRequest;
import br.com.idolink.api.dto.response.ConfigFaqResponse;

public interface ConfigFaqService {

	ConfigFaqResponse findById(Long id);

	ConfigFaqResponse findByIdo(Long idoid);

	ConfigFaqResponse create(Long idoid, ConfigFaqRequest request);

	ConfigFaqResponse update(ConfigFaqRequest request, Long id);

	void delete(Long id);

	ConfigFaqResponse findByIdo(Long idoid, boolean validation);
	
	ConfigFaqResponse publicFindByIdo(Long idoid, boolean validation);
	
	ConfigFaqResponse publicFindByIdo(Long idoid);
	
}
