package br.com.idolink.api.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class GeneralSettingsResponse {

	private Long id;
	
	private LanguageResponse language;
	
	private Boolean sensitiveContent;

	@JsonIgnore
	private UserStatusResponse status;
}
