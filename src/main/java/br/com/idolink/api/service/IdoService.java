package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.IdoContactRequest;
import br.com.idolink.api.dto.request.IdoSettingsRequest;
import br.com.idolink.api.dto.request.WallpaperIdoRequest;
import br.com.idolink.api.dto.request.ido.IdoProfileRequest;
import br.com.idolink.api.dto.request.ido.IdoRequest;
import br.com.idolink.api.dto.response.IdoContactResponse;
import br.com.idolink.api.dto.response.IdoResponse;
import br.com.idolink.api.dto.response.WallpaperIdoResponse;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.enums.PredefinedModelsTemplate;

public interface IdoService {

	IdoResponse findById(Long id);
	
	IdoResponse publicFindById(Long id);
	
	IdoContactResponse getPublicContact(Long id);

	IdoResponse create(IdoRequest request);

	IdoResponse update(Long id, IdoProfileRequest request);

	void delete(Long id);

	/**
	 * cria a estrutura inicial de um ido, setando o template padrao como branco
	 * @param request
	 * @param userId
	 * @return
	 */
	IdoResponse createProfile(IdoProfileRequest request, Long userId);

	IdoContactResponse saveContact(Long idIdo,IdoContactRequest request, boolean isUpdate);
	
	IdoContactResponse getContact(Long id);

	void deleteContact(Long id);

	void validateDomain(String name);

	List<IdoResponse> findIdoByUser(Long userId);
	
	Ido validate(Long id);

	/**
	 * Salva um papel de parede em um ido
	 * @param idoId
	 * @param request
	 * @return
	 */
	WallpaperIdoResponse setWallpaperIdo(Long idoId, WallpaperIdoRequest request);

	/**
	 * atualiza o icone do ido
	 * @param icon
	 * @param idoId
	 * @return
	 */
	IdoResponse UpdatePhotoIdo(Long icon, Long idoId);

	IdoResponse changeSettings(Long idoId, IdoSettingsRequest request, Long usuarioId);

	/**
	 * verifica se o dominio pode alterar respeitando as regras de alteracao
	 * @param ido
	 */
	void validateChangeDomain(Long ido);
	
	IdoResponse findByDomain(String domain);
	
	List<Ido> findModelByUser(Long id);

	IdoResponse saveTemplateIdo(PredefinedModelsTemplate template, Long idoId, Boolean isNew);

	IdoResponse findByDomainPublic(String domain);

	IdoResponse addToolsPositions(IdoResponse response);
		
}
