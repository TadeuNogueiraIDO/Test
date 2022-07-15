package br.com.idolink.api.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LanguageRequest {

	private Long id;

	@JsonIgnore
	private String name;
	
	@JsonIgnore
	private String locale;

}
