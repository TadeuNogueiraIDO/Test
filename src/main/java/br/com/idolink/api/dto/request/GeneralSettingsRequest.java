package br.com.idolink.api.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeneralSettingsRequest {

	@JsonIgnore
	private UserRequest user;
	
	private LanguageRequest language;

	private Boolean sensitiveContent;
	
	@JsonIgnore
	private UserStatusRequest status;

}
