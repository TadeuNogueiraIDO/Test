package br.com.idolink.api.service;

import br.com.idolink.api.dto.response.IdoResponse;

public interface IdoDraftService {

	/**
	 * carrega um rascunho para o ido. Se o ido ainda nao foi publicado, o rascunho sera o proprio ido. 
	 * Caso contrario, uam copia do ido eh criado e tratado como rascunho do ido
	 * @param idoId
	 * @return
	 */
	IdoResponse loadDraftIdo(Long idoId);

	/**
	 * public o ido que esta em rascunho. Caso tenho um ido anterior publicado, esse ido eh excluido
	 * @param idoId
	 * @return
	 */
	IdoResponse publishIdo(Long idoId);

	IdoResponse loadDraftByIdoPublic(String idoId);
			
}
