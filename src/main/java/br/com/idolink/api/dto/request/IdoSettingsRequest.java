package br.com.idolink.api.dto.request;

import java.util.List;

import br.com.idolink.api.dto.response.TimezoneResponse;
import br.com.idolink.api.model.enums.IdoStatus;
import lombok.Data;

@Data
public class IdoSettingsRequest {
	
	private String name;
	
	private String domain;

	private IdoStatus status;
	
	private Boolean hideIdolink;
	
	private Boolean sensitiveContent;
	
	private IdoSharingThumbRequest sharingThumb;
	
	private List<CategoryRequest> categories;
	
	private TimezoneResponse timezone;
		
}
