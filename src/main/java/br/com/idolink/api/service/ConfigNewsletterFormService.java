package br.com.idolink.api.service;

import br.com.idolink.api.dto.request.ConfigNewsletterFormRequest;
import br.com.idolink.api.dto.response.ConfigNewsletterFormResponse;

public interface ConfigNewsletterFormService {

	ConfigNewsletterFormResponse findByIdo(Long idoId);
	
	ConfigNewsletterFormResponse save(Long idoId, ConfigNewsletterFormRequest request);
	
	ConfigNewsletterFormResponse update(Long id, ConfigNewsletterFormRequest request);
	
	void delete(Long id);

	ConfigNewsletterFormResponse findById(Long id);

	ConfigNewsletterFormResponse findByIdo(Long idoId, boolean validation);
	
	ConfigNewsletterFormResponse publicFindByIdo(Long idoId);
	
	ConfigNewsletterFormResponse publicFindByIdo(Long idoId, boolean validation);

}

