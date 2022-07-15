package br.com.idolink.api.service;

import java.util.List;
import java.util.Optional;

import br.com.idolink.api.dto.request.AppearanceTextRequest;
import br.com.idolink.api.dto.response.AppearanceTextResponse;
import br.com.idolink.api.model.AppearanceText;
import br.com.idolink.api.model.Ido;

public interface AppearanceTextService {
	
	List<AppearanceTextResponse> list();

	AppearanceTextResponse create(Long idoId, AppearanceTextRequest request);

	AppearanceTextResponse update(Long id, AppearanceTextRequest request);
	
	AppearanceTextResponse findByIdo(Long id);

	AppearanceTextResponse findByIdo(Long id, boolean validation);
	
	AppearanceTextResponse publicFindByIdo(Long id);
	
	AppearanceTextResponse publicFindByIdo(Long id, boolean validation);

	Optional<AppearanceText> findByIdo(Ido ido);

	void delete(Long id);

	AppearanceText save(AppearanceText model);

}
