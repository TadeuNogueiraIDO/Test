package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.PredefinedModelRequest;
import br.com.idolink.api.dto.response.AppearanceButtonResponse;
import br.com.idolink.api.dto.response.AppearanceCardsResponse;
import br.com.idolink.api.dto.response.AppearanceTextResponse;
import br.com.idolink.api.dto.response.PredefinedModelResponse;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.enums.PredefinedModelsTemplate;


public interface PredefinedModelService {

	 List <PredefinedModelResponse> list();
    
	 PredefinedModelResponse findById(Long id);

	 PredefinedModelResponse create(PredefinedModelRequest request);

	 PredefinedModelResponse update(Long id, PredefinedModelRequest request);

     void delete(Long id);

     void setWallpaperTemplate(PredefinedModelsTemplate predefModel, Ido ido, boolean save, boolean isNew);

	AppearanceButtonResponse setTemplateAppearanceButton(Long idoId);

	AppearanceButtonResponse getTemplateAppearanceButton(Long idoId);

	AppearanceCardsResponse getTemplateAppearanceCard(Long idoId);

	AppearanceCardsResponse setTemplateAppearanceCard(Long idoId);

	AppearanceTextResponse setTemplateAppearanceText(Long idoId);

	AppearanceTextResponse getTemplateAppearanceText(Long idoId);

    
}