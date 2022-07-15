package br.com.idolink.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.request.AppearanceButtonRequest;
import br.com.idolink.api.dto.response.AppearanceButtonResponse;
import br.com.idolink.api.dto.response.ButtonShapeResponse;
import br.com.idolink.api.model.AppearanceButton;
import br.com.idolink.api.model.Ido;

public interface AppearanceButtonService {

	List<AppearanceButtonResponse> list();

	AppearanceButtonResponse findById(Long id);

	AppearanceButtonResponse create(Long idoId, AppearanceButtonRequest request);

	AppearanceButtonResponse update(Long id, AppearanceButtonRequest request);

	ResponseEntity<List<ButtonShapeResponse>> findAll();

	AppearanceButtonResponse findByIdo(Long id);

	AppearanceButton save(AppearanceButton model);

	Optional<AppearanceButton> findByIdo(Ido ido);

	void delete(Long id);

	AppearanceButtonResponse findByIdo(Long id, boolean validation);

	AppearanceButtonResponse publicFindByIdo(Long id, boolean validation);

	AppearanceButtonResponse publicFindByIdo(Long id);

	/*
	 * altera a aparencia do botao padrao do ido para igual a do template
	 * 
	 * AppearanceButtonResponse setTemplateAppearanceButton(Long idoId);
	 * 
	 * 
	 * Busca a aparencia do botao padrao do template utilizado pelo ido
	 * 
	 * AppearanceButtonResponse getTemplateAppearanceButton(Long idoId);
	 */

}
