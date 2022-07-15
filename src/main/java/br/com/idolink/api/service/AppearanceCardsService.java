package br.com.idolink.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.request.AppearanceCardsRequest;
import br.com.idolink.api.dto.response.AppearanceCardsResponse;
import br.com.idolink.api.dto.response.ButtonShapeResponse;
import br.com.idolink.api.model.AppearanceCards;
import br.com.idolink.api.model.Ido;

public interface AppearanceCardsService {

	List<AppearanceCardsResponse> list();

	AppearanceCardsResponse findById(Long id);

	AppearanceCardsResponse create(Long idoId, AppearanceCardsRequest request);

	AppearanceCardsResponse update(Long id, AppearanceCardsRequest request);

	ResponseEntity<List<ButtonShapeResponse>> findAll();

	AppearanceCardsResponse findByIdo(Long id);

	AppearanceCards save(AppearanceCards model);

	void delete(Long id);

	Optional<AppearanceCards> findByIdo(Ido ido);

	AppearanceCardsResponse findByIdo(Long id, boolean validation);
	
	AppearanceCardsResponse publicFindByIdo(Long id);
	
	AppearanceCardsResponse publicFindByIdo(Long id, boolean validation);

	/*
	 * AppearanceCardsResponse setTemplateAppearanceCard(Long idoId);
	 * 
	 * AppearanceCardsResponse getTemplateAppearanceCard(Long idoId);
	 */

}
