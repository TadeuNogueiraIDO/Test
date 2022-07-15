package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.GeneralSettingsRequest;
import br.com.idolink.api.dto.request.UserStatusRequest;
import br.com.idolink.api.dto.response.GeneralSettingsResponse;
import br.com.idolink.api.dto.response.LanguageResponse;
import br.com.idolink.api.dto.response.UserStatusResponse;
import br.com.idolink.api.model.GeneralSettings;
import br.com.idolink.api.model.User;

public interface GeneralSettingsService {

	List<LanguageResponse> listLanguages();

	GeneralSettingsResponse update(Long id, GeneralSettingsRequest request);

	UserStatusResponse updateStatus(Long id, UserStatusRequest request);

	void delete(Long id);

	GeneralSettingsResponse findByUser(Long userId);
	
	GeneralSettings create(GeneralSettingsRequest request, User user);
	
	public void createSave(GeneralSettingsRequest request, User user) ;
}
